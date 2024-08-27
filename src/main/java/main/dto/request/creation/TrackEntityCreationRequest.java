/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Optional;
import main.validation.ValidTrackAction;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record TrackEntityCreationRequest(
        @PastOrPresent
        LocalDateTime date,
        @NotBlank(message="evidence Id is mandatory")
        Integer evidenceId,
        @NotBlank(message="detective Id is mandatory")
        Integer detectiveId,
        @ValidTrackAction
        String trackAction,
        Optional<String> reason
        ) {

}
