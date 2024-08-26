/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author hp
 */
public record TrackEntityCreationRequest(
        LocalDateTime date,
        Integer evidenceId,
        Integer detectiveId,
        String trackAction,
        Optional<String> reason
        ) {

}
