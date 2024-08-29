/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.event_listeners;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.events.DetectivePersonUpdateEvent;
import main.events.DetectivePersonUpdateEventFailure;
import main.service.PersonService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=AccessLevel.PRIVATE)
@Service
public class DetectivePersonUpdateService {
    PersonService service;
    ApplicationEventPublisher eventPublisher;
    
    @EventListener
    public void updatePerson(DetectivePersonUpdateEvent event){
        try{
            service.update(event.getPersonId(), event.getRequest());
        }catch(Exception e){
            eventPublisher.publishEvent(new DetectivePersonUpdateEventFailure(this,"Detective Person Update Opeartion Failed"));
        }
    }
    
}
