/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import java.util.Optional;

/**
 *
 * @author hp
 */
public record EvidenceCreationRequest(
        String criminalCase,
        String evidenceNumber,
        String itemName,
        Optional<Integer> storageId,
        Optional<Boolean> archived
        ) {

}
