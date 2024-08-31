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
import main.dto.request.creation.EvidenceCreationRequest;
import main.dto.request.update.EvidenceUpdateRequest;
import main.dto.response.EvidenceResponse;
import main.exception.AlreadyExistsException;
import main.exception.EntityNotFoundException;
import main.repo.CriminalCaseRepo;
import main.repo.EvidenceRepo;
import main.repo.StorageRepo;
import main.util.mapper.EvidenceMapper;
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
public class EvidenceService {
    EvidenceMapper mapper;
    EvidenceRepo repo;
    CriminalCaseRepo caseRepo;
    StorageRepo storageRepo;
    @NonFinal Validator validator;
    
    @Transactional
    public EvidenceResponse create(EvidenceCreationRequest x){
        log.info("creating new Evidence with evidenceNumber {}," + x.evidenceNumber());
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Evidence Creation Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(repo.existsByEvidenceNumber(x.evidenceNumber())){
            log.warn("Trying to create a new Evidence with already existing evidenceNumber {}",x.evidenceNumber());
            throw new AlreadyExistsException("evidenceNumber: " + x.evidenceNumber()+ " already exists");
        }
        var evidence = mapper.toEntity(x);
        var savedEvidence = repo.save(evidence);
        log.info("successfully creating a new Evidence with Id: {} and evidenceNumber: {}",savedEvidence.getId(),savedEvidence.getEvidenceNumber());
        return mapper.toDTO(savedEvidence);
    }
    
    
    @Transactional
    public EvidenceResponse update(Integer id, EvidenceUpdateRequest x){
        log.info("updating Evidence with id: {}",id);
        var violations = validator.validate(x);
        if(!violations.isEmpty()){
            log.warn("The Evidence Update Request failed the validation barrier");
            throw new ConstraintViolationException(violations);
        }
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        var evidence = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evidence with id: " + id + " doesn't exists"))
                .setItemName(x.itemName());
        caseRepo.findById(x.criminalCaseId()).ifPresent(evidence::setCriminalCase);
        storageRepo.findById(x.storageId()).ifPresent(evidence::setStorage);
        x.archived().ifPresent(evidence::setArchived);
        x.notes().ifPresent(evidence::setNotes);
        
        var savedEvidence = repo.save(evidence);
        log.info("successfully updating a Evidence with Id: {} and evidenceNumber: {}",savedEvidence.getId(),savedEvidence.getEvidenceNumber());
        return mapper.toDTO(savedEvidence);
    }
    
    @Transactional
    public void delete(Integer id){
        log.info("Deleting Evidence with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        repo.findById(id).ifPresent(repo::delete);
        log.info("Successfully Deleting Evidence with id: {}", id);
    }
    
    public EvidenceResponse findById(Integer id){
        log.info("retrieving Evidence with Id: {}",id);
        if(id==null || id<1){
            log.warn("The user entered either a null Id or an Id less than 1");
            throw new IllegalArgumentException("Id must be non null and positive");
        }
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Evidence with id: " + id + " doesn't exists"));
    }
    
    public Page<EvidenceResponse> findAll(int pages, int size){
        return repo.findAll(PageRequest.of(pages, size))
                .map(mapper::toDTO);
    }
}
