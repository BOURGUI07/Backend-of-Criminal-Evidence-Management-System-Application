/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.response;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import main.util.enums.EmploymentStatus;
import main.util.enums.Rank;

/**
 *
 * @author hp
 */
public record DetectiveResponse(
        Integer id,
        PersonResponse personResponse,
        String badgeNumber,
        Rank rank,
        Optional<Boolean> armed,
        EmploymentStatus employmentStatus,
        Set<Integer> criminalCaseIds,
        Set<Integer> trackEntityIds,
        Instant createdDate
        ) {

}
