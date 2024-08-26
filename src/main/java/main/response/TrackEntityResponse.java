/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.response;

import java.time.Instant;
import java.util.Optional;
import main.util.enums.TrackAction;

/**
 *
 * @author hp
 */
public record TrackEntityResponse(
        Integer id,
        Integer evidenceId,
        String evidenceNumber,
        Integer detectiveId,
        String detectiveFirstName,
        String detectiveLastName,
        TrackAction trackAction,
        Optional<String> reason,
        Integer version,
        Instant createdDate
        ) {

}
