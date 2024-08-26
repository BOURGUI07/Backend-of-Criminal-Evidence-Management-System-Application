/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.response;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import main.util.enums.CaseStatus;
import main.util.enums.CaseType;

/**
 *
 * @author hp
 */
public record CriminalCaseResponse(
        Integer id,
        CaseType caseType,
        String shortdesc,
        Optional<String> longDesc,
        Optional<String> notes,
        CaseStatus caseStatus,
        Integer leadDetectiveId,
        String leadFirstName,
        String leadLastName,
        Set<Integer> detectiveIds,
        Set<Integer> evidenceIds,
        Integer version,
        Instant createdDate
        ) {

}
