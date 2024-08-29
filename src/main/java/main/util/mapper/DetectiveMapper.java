/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util.mapper;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.dto.request.creation.DetectiveCreationRequest;
import main.dto.response.DetectiveResponse;
import main.entity.Detective;
import main.repo.PersonRepo;
import main.service.PersonService;
import main.util.enums.EmploymentStatus;
import main.util.enums.Rank;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
public class DetectiveMapper {
    PersonService service;
    PersonRepo personRepo;
    
    public Detective toEntity(DetectiveCreationRequest x){
        var d = new Detective()
                .setBadgeNumber(x.badgeNumber())
                .setRank(Rank.fromString(x.rank()))
                .setStatus(EmploymentStatus.fromString(x.employmentStatus()));
                x.armed().ifPresent(d::setArmed);
        var savedPerson = service.create(x.request());
        personRepo.findById(savedPerson.id()).ifPresent(d::setPerson);
        return d;    
    }
    
    public DetectiveResponse toDTO(Detective d){
        var criminalCaseSet = d.getCriminalCases().stream().map(x->x.getId()).collect(Collectors.toUnmodifiableSet());
        var trackEntitySet = d.getTrackEntities().stream().map(x->x.getId()).collect(Collectors.toUnmodifiableSet());
        var personResponse  = service.findById(d.getPerson().getId());
        return  new DetectiveResponse(d.getId(),
                                      personResponse,
                                      d.getBadgeNumber(),
                                      d.getRank(),
                                      d.getArmed(),
                                      d.getStatus(),
                                      criminalCaseSet,
                                      trackEntitySet,
                                      d.getCreatedDate());
    }
}
