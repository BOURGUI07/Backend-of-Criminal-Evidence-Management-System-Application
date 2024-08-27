/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package main.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author hp
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public record StorageRequest(
        @NotBlank(message="storage name is mandatory")
                @Size(max=100,message="storage name shouldn't exceed 100 charcaters")
        String name,
        @NotBlank(message="storage location is mandatory")
                @Size(max=200,message="storage location shouldn't exceed 200 charcaters")
        String location
        ) {

}
