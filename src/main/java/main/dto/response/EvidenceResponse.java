/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.response;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author hp
 */
public record EvidenceResponse(
        Integer id,
        Integer criminalCaseId,
        String evidenceNumber,
        String itemName,
        Optional<Integer> storageId,
        Optional<String> storageName,
        Boolean archived,
        Set<Integer> trackEntityIds,
        Integer version,
        Instant createdDate
        ) {

}
