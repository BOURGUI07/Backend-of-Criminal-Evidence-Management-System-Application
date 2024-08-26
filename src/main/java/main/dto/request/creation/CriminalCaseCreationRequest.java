/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import java.util.Optional;
import java.util.Set;

/**
 *
 * @author hp
 */
public record CriminalCaseCreationRequest(
        String caseNumber,
        String caseType,
        String shortdesc,
        Optional<String> longDesc,
        Optional<String> notes,
        String caseStatus,
        Integer leadDetectiveId,
        Set<Integer> detectiveIds,
        Set<Integer> evidenceIds
        ) {

}
