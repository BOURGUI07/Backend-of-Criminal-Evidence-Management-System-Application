/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util.mapper;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.dto.request.StorageRequest;
import main.dto.response.StorageResponse;
import main.entity.Storage;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
public class StorageMapper {
    public Storage toEntity(StorageRequest x){
        return new Storage().setLocation(x.location()).setName(x.name());
    }
    
    public StorageResponse toDTO(Storage s){
        return new StorageResponse(s.getId(),s.getName(),s.getLocation(),
            s.getEvidences().stream().map(x->x.getId()).collect(Collectors.toUnmodifiableSet()),
            s.getVersion(),s.getCreatedDate());
    }
}
