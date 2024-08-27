/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.util.enums;

/**
 *
 * @author hp
 */
public enum CaseStatus {
    SUBMITTED,UNDER_INVESTIGATION,IN_COURT,CLOSED,DISMISSED,COLD;
    
    public static CaseStatus fromString(String status){
        for(var x:CaseStatus.values()){
            if(x.name().equalsIgnoreCase(status)){
                return x;
            }
        }
        throw new IllegalArgumentException("Unknow enum type for: " + status);
    }
}
