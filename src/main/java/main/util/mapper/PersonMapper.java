/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util.mapper;


import main.dto.request.creation.PersonCreationRequest;
import main.dto.response.PersonResponse;
import main.entity.Person;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class PersonMapper {
    public Person toEntity(PersonCreationRequest x){
        return new Person().setFirstname(x.firstname())
                .setHiringDate(x.hiringDate())
                .setLastname(x.lastname())
                .setPassword(x.password())
                .setUsername(x.username());
    }
    
    public PersonResponse toDTO(Person p){
        return new PersonResponse(p.getId(),p.getUsername(),p.getFirstname(),p.getLastname(),p.getHiringDate(),p.getCreatedDate());
    }
}
