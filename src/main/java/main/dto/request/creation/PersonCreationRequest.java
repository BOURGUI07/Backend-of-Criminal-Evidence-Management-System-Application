/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request.creation;

import java.time.LocalDateTime;

/**
 *
 * @author hp
 */
public record PersonCreationRequest(
        String username,
        String firstname,
        String lastname,
        String password,
        LocalDateTime hiringDate
        ) {

}
