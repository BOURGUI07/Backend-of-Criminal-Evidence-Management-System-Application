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
import main.dto.request.creation.EvidenceCreationRequest;
import main.dto.response.EvidenceResponse;
import main.entity.Evidence;
import main.repo.CriminalCaseRepo;
import main.repo.StorageRepo;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
public class EvidenceMapper {
    StorageRepo storageRepo;
    CriminalCaseRepo caseRepo;
    
    
    public Evidence toEntity(EvidenceCreationRequest x){
        var e = new Evidence()
                .setEvidenceNumber(x.evidenceNumber())
                .setItemName(x.itemName());
        x.archived().ifPresent(e::setArchived);
        x.notes().ifPresent(e::setNotes);
        caseRepo.findById(x.criminalCaseId()).ifPresent(e::setCriminalCase);
        x.storageId().ifPresent(storageId -> {
            storageRepo.findById(storageId).ifPresent(e::setStorage);
        });
        return e;
    }
    
    
    public EvidenceResponse toDTO(Evidence e){
        var set = e.getTrackEntities().stream().map(x-> x.getId()).collect(Collectors.toUnmodifiableSet());
        var storage = e.getStorage();
        return new EvidenceResponse(e.getId(),
                e.getCriminalCase().getId(),
                e.getEvidenceNumber(),
                e.getItemName(),
                Optional.ofNullable(storage.getId()),
                Optional.ofNullable(storage.getName()),
                e.getArchived(),
                set,
                e.getVersion(),
                e.getCreatedDate());
    }
}
