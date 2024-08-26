/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.util.enums;

/**
 *
 * @author hp
 */
public enum TrackAction {
    SUBMITTED,RETRIEVED,RETURNED;
    
    public static TrackAction fromString(String trackAction){
        for(var x:TrackAction.values()){
            if(x.name().equalsIgnoreCase(trackAction)){
                return x;
            }
        }
        throw new IllegalArgumentException("Unknow enum type: " + trackAction);
    }
}
