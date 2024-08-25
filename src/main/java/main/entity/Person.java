/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author hp
 */
@Entity
@Table(name="person",indexes={
    @Index(name="idx_person_id",columnList="id"),
    @Index(name="idx_person_username",columnList="username")
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain=true)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Person extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    Integer id;
    
    @Column(name="username",unique=true,nullable=false,length=50)
    String username;
    
    
    @Column(name="firstname",nullable=false,length=50)
    String firstname;
    
    
    @Column(name="lastname",nullable=false,length=50)
    String lastname;
    
    
    @Column(name="password",unique=true,nullable=false,length=16)
    String password;
    
    
    @Column(name="hiring_date",nullable=false,updatable=false)
    LocalDateTime hiringDate;
    
}
