/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util.mapper;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.dto.request.creation.CriminalCaseCreationRequest;
import main.dto.response.CriminalCaseResponse;
import main.entity.CriminalCase;
import main.repo.DetectiveRepo;
import main.repo.EvidenceRepo;
import main.util.enums.CaseStatus;
import main.util.enums.CaseType;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
public class CriminalCaseMapper {
    EvidenceRepo evidenceRepo;
    DetectiveRepo repo;
    
    public CriminalCase toEnttiy(CriminalCaseCreationRequest x){
        var c = new CriminalCase()
                .setCaseNumber(x.caseNumber())
                .setCaseType(CaseType.fromString(x.caseType()))
                .setCaseStatus(CaseStatus.fromString(x.caseStatus()))
                .setShortDesc(x.shortdesc())
                .setDetectives(repo.findAllById(x.detectiveIds()))
                .setEvidences(evidenceRepo.findAllById(x.evidenceIds()));
        x.notes().ifPresent(c::setNotes);
        x.longDesc().ifPresent(c::setLongDesc);
        repo.findById(x.leadDetectiveId()).ifPresent(c::setLeadDetective);
        return c;
    }
    
    public CriminalCaseResponse toDTO(CriminalCase c){
        var evidenceSet = c.getEvidences().stream().map(x-> x.getId()).collect(Collectors.toUnmodifiableSet());
        var detectiveSet = c.getDetectives().stream().map(x-> x.getId()).collect(Collectors.toUnmodifiableSet());
        var leadDetective = c.getLeadDetective();
        return new CriminalCaseResponse(c.getId(),
                                        c.getCaseNumber(),
                                        c.getCaseType(),
                                        c.getShortDesc(),
                                        Optional.ofNullable(c.getLongDesc()),
                                        Optional.ofNullable(c.getNotes()),
                                        c.getCaseStatus(),
                                        leadDetective.getId(),
                                        leadDetective.getPerson().getFirstname(),
                                        leadDetective.getPerson().getLastname(),
                                        detectiveSet,
                                        evidenceSet,
                                        c.getVersion(),
                                        c.getCreatedDate()
                                        
        );
    }
}
