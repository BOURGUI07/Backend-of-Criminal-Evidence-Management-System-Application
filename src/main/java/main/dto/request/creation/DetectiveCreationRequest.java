/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto.request.creation;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author hp
 */
public record DetectiveCreationRequest(
        String username,
        String firstname,
        String lastname,
        String password,
        LocalDateTime hiringDate,
        String badgeNumber,
        String rank,
        Optional<Boolean> armed,
        String employmentStatus
        ) {
    
}
