/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record EvidenceCreationRequest(
        @NotBlank(message="criminal case Id is mandatory")
        Integer criminalCaseId,
        @NotBlank(message="evidence number is mandatory")
        String evidenceNumber,
        @NotBlank(message="item name is mandatory")
        String itemName,
        Optional<Integer> storageId,
        Optional<Boolean> archived
        ) {

}
