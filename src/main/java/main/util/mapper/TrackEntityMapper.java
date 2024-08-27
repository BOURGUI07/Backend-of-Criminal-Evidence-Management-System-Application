/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util.mapper;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.dto.request.creation.TrackEntityCreationRequest;
import main.dto.response.TrackEntityResponse;
import main.entity.TrackEntity;
import main.repo.DetectiveRepo;
import main.repo.EvidenceRepo;
import main.util.enums.TrackAction;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
public class TrackEntityMapper {
    EvidenceRepo evidenceRepo;
    DetectiveRepo repo;
    
    public TrackEntity toEntity(TrackEntityCreationRequest x){
        var t = new TrackEntity().setAction(TrackAction.fromString(x.trackAction())).setDate(x.date());
        x.reason().ifPresent(t::setReason);
        repo.findById(x.detectiveId()).ifPresent(t::setDetective);
        evidenceRepo.findById(x.evidenceId()).ifPresent(t::setEvidence);
        return t;
    }
    
    public TrackEntityResponse toDTO(TrackEntity t){
        var evidence = t.getEvidence();
        var detective = t.getDetective();
        return new TrackEntityResponse(t.getId(),evidence.getId(),evidence.getEvidenceNumber(),detective.getId(),
                                        detective.getPerson().getFirstname(),detective.getPerson().getLastname(),
                                        t.getAction(),Optional.ofNullable(t.getReason()),t.getVersion(),t.getCreatedDate());
    }
}
