/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.update;

import java.util.Optional;

/**
 *
 * @author hp
 */
public record EvidenceUpdateRequest(
        Integer criminalCaseId,
        Integer storageId,
        String itemName,
        Optional<Boolean> archived,
        Optional<String> notes
        ) {

}
