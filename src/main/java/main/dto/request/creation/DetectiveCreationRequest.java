/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import main.validation.ValidEmploymentStatus;
import main.validation.ValidRank;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record DetectiveCreationRequest(
        @NotNull
        PersonCreationRequest request,
        @NotBlank(message="detective badge number is mandatory")
        String badgeNumber,
        @ValidRank
        String rank,
        Optional<Boolean> armed,
        @ValidEmploymentStatus
        String employmentStatus
        ) {
    
}
