/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Optional;
import java.util.Set;
import main.validation.ValidCaseStatus;
import main.validation.ValidCaseType;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record CriminalCaseCreationRequest(
        @NotBlank(message="criminal case number is mandatory")
        String caseNumber,
        @ValidCaseType(message="Invalid Casetype, please enter one of the following types: UNCATEGORIZED,INFRACTION,MISDEMEANOR,FELONY")
        String caseType,
        @NotBlank(message="criminal case short description is mandatory")
        String shortdesc,
        Optional<String> longDesc,
        Optional<String> notes,
        @ValidCaseStatus
        String caseStatus,
        @NotBlank(message="lead detective id is mandatory")
        Integer leadDetectiveId,
        @NotEmpty(message="list of detective id shouldn't be empty")
        Set<Integer> detectiveIds,
        @NotEmpty(message="list of evidence id shouldn't be empty")
        Set<Integer> evidenceIds
        ) {

}
