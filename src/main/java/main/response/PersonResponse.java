/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.response;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 *
 * @author hp
 */
public record PersonResponse(
        Integer id,
        String username,
        String firstname,
        String lastname,
        LocalDateTime hiringDate,
        Instant createdDate
        ) {

}
