/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.response;

import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author hp
 */
public record PersonResponse(
        Integer id,
        String username,
        String firstname,
        String lastname,
        LocalDate hiringDate,
        Instant createdDate
        ) {

}
