/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.util.enums;

/**
 *
 * @author hp
 */
public enum EmploymentStatus {
    ACTIVE,
    SUSPENDED,
    VACATION,
    UNDER_INVESTIGATION,
    RETIRED;
    
    public static EmploymentStatus fromString(String status){
        for(var x:EmploymentStatus.values()){
            if(x.toString().equalsIgnoreCase(status)){
                return x;
            }
        }
        throw new IllegalArgumentException("Unknow enum type: " + status);
    }
}
