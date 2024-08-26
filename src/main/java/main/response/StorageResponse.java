/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.response;

import java.time.Instant;
import java.util.Set;

/**
 *
 * @author hp
 */
public record StorageResponse(
        Integer id,
        String name,
        String location,
        Set<Integer> evidenceIds,
        Integer version,
        Instant createdDate
        ) {

}
