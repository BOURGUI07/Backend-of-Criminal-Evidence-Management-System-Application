/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.util.enums;

/**
 *
 * @author hp
 */
public enum CaseType {
    UNCATEGORIZED,
    INFRACTION,
    MISDEMEANOR,
    FELONY;
    
    public static CaseType fromString(String caseTypeName){
        for(var x:CaseType.values()){
            if(x.name().equalsIgnoreCase(caseTypeName)){
                return x;
            }
        }
        throw new IllegalArgumentException("Unknow enum type for: " + caseTypeName);
    }
}
