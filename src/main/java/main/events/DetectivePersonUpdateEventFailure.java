/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author hp
 */
@Getter
@Setter
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class DetectivePersonUpdateEventFailure extends ApplicationEvent{
    String message;
    
    public DetectivePersonUpdateEventFailure(Object source,String msg) {
        super(source);
        message=msg;
    }
    
}
