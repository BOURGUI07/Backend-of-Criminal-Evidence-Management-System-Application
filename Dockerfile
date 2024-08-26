# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-22 AS build
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml . 
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:22-jdk-slim
VOLUME /tmp

# ARG for specifying the JAR file, with a default value
ARG JAR_FILE=target/blog-0.0.1-SNAPSHOT.jar

# Copy the built JAR file to the container
COPY --from=build /app/${JAR_FILE} /app/app.jar

# Specify the entry point for running the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

