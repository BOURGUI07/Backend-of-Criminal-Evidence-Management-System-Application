/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import main.dto.request.creation.PersonCreationRequest;
import main.dto.request.update.PersonUpdateRequest;
import main.dto.response.PersonResponse;
import main.exception.AlreadyExistsException;
import main.exception.EntityNotFoundException;
import main.repo.PersonRepo;
import main.util.mapper.PersonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
@Slf4j
@Setter
public class PersonService {
    PersonMapper mapper;
    PersonRepo repo;
    @NonFinal Validator validator;
    
    @Transactional
    public PersonResponse create(PersonCreationRequest x){
        log.info("creating new Person with username {}," + x.username());
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Person Creation Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(repo.existsByUsernameIgnoreCase(x.username())){
            log.warn("Trying to create a new Person with already existing username {}",x.username());
            throw new AlreadyExistsException("username: " + x.username() + " already exists");
        }
        var person = mapper.toEntity(x);
        var savedPerson = repo.save(person);
        log.info("successfully creating a new Person with Id: {} and username: {}",savedPerson.getId(),savedPerson.getUsername());
        return mapper.toDTO(savedPerson);
    }
    
    
    @Transactional
    public PersonResponse update(Integer id, PersonUpdateRequest x){
        log.info("updating Person with id: {}",id);
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Person Update Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        var person = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person with id: " + id + " doesn't exists"))
                .setFirstname(x.firstname())
                .setLastname(x.lastname())
                .setUsername(x.username());
        var savedPerson = repo.save(person);
        log.info("successfully updating a Person with Id: {} and username: {}",savedPerson.getId(),savedPerson.getUsername());
        return mapper.toDTO(savedPerson);
    }
    
    @Transactional
    public void delete(Integer id){
        log.info("Deleting Person with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        repo.findById(id).ifPresent(repo::delete);
        log.info("Successfully Deleting Person with id: {}", id);
    }
    
    public PersonResponse findById(Integer id){
        log.info("retrieving Person with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Person with id: " + id + " doesn't exists"));
    }
    
    public Page<PersonResponse> findAll(int pages, int size){
        return repo.findAll(PageRequest.of(pages, size))
                .map(mapper::toDTO);
    }
}
