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
public record DetectiveUpdateRequest(
        PersonUpdateRequest request,
        String rank,
        Optional<Boolean> armed,
        String employmentStatus
        ) {

}
