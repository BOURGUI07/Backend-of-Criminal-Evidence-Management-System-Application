/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record PersonCreationRequest(
        @NotBlank(message="username is mandatory")
                @Size(min=5,max=100)
        String username,
        @NotBlank(message="firstname is mandatory")
                @Size(min=5,max=100)
        String firstname,
        @NotBlank(message="lastname is mandatory")
                @Size(min=5,max=100)
        String lastname,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
             message = "password must be at least 8 characters,"
                     + " one lowercase letter,"
                     + " one uppercase letter,"
                     + " one number,"
                     + " and one special character")
        String password,
        @PastOrPresent
        LocalDateTime hiringDate
        ) {

}
