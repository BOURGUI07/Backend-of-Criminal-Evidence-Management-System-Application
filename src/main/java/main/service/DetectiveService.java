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
import main.dto.request.creation.DetectiveCreationRequest;
import main.dto.request.update.DetectiveUpdateRequest;
import main.dto.response.DetectiveResponse;
import main.dto.response.PersonResponse;
import main.events.DetectivePersonUpdateEvent;
import main.events.DetectivePersonUpdateEventFailure;
import main.exception.AlreadyExistsException;
import main.exception.EntityNotFoundException;
import main.repo.DetectiveRepo;
import main.util.enums.EmploymentStatus;
import main.util.enums.Rank;
import main.util.mapper.DetectiveMapper;
import org.springframework.context.ApplicationEventPublisher;
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
public class DetectiveService {
    DetectiveRepo repo;
    DetectiveMapper mapper;
    ApplicationEventPublisher eventPublisher;
    @NonFinal Validator validator;
    
    @Transactional
    public DetectiveResponse create(DetectiveCreationRequest x){
        var requestBadgeNumber = x.badgeNumber();
        log.info("creating new Detective with badgeNumber {}", requestBadgeNumber);
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Detective Creation Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(repo.existsByBadgeNumberIgnoreCase(requestBadgeNumber)){
            log.warn("Trying to create a new Detective with already existing badgeNumber {}",requestBadgeNumber);
            throw new AlreadyExistsException("badgeNumber: " + requestBadgeNumber + " already exists");
        }
        var detective = mapper.toEntity(x);
        var savedDetective = repo.save(detective);
        log.info("successfully creating a new Detecive with Id: {} and badgeNumber: {}",savedDetective.getId(),savedDetective.getBadgeNumber());
        return mapper.toDTO(savedDetective);
    }
    
    @Transactional
    public DetectiveResponse update(Integer id, DetectiveUpdateRequest x){
        log.info("updating Detective with id: {}",id);
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Detective Update Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        var detective = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detective with id: " + id + " doesn't exists"))
                .setStatus(EmploymentStatus.fromString(x.employmentStatus()))
                .setRank(Rank.fromString(x.rank()));
        x.armed().ifPresent(detective::setArmed);
        try{
            log.info("Launching the detective person update event");
            eventPublisher.publishEvent(new DetectivePersonUpdateEvent(this,detective.getPerson().getId(),x.request()));
        }catch(Exception e){
            log.warn("Failed to update detective person profile");
            eventPublisher.publishEvent(new DetectivePersonUpdateEventFailure(this, "Failed to update the detective person profile"));
        }
        var savedDetective = repo.save(detective);
        log.info("successfully updating a Detective with Id: {} and badgeNumber: {}",savedDetective.getId(),savedDetective.getBadgeNumber());
        return mapper.toDTO(savedDetective);
    }
    
    @Transactional
    public void delete(Integer id){
        log.info("Deleting detective with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        repo.findById(id).ifPresent(repo::delete);
        log.info("Successfully Deleting detective with id: {}", id);
    }
    
    public DetectiveResponse findById(Integer id){
        log.info("retrieving detective with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("detective with id: " + id + " doesn't exists"));
    }
    
    public Page<DetectiveResponse> findAll(int pages, int size){
        return repo.findAll(PageRequest.of(pages, size))
                .map(mapper::toDTO);
    }
}
