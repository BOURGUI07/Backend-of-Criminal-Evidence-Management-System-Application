/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.util.enums;

/**
 *
 * @author hp
 */
public enum Rank {
    TRAINEE,JUNIOR,SENIOR,INSPECTOR,CHIEF_INSPECTOR;
    
    public static Rank fromString(String rank){
        for(var x:Rank.values()){
            if(x.toString().equalsIgnoreCase(rank)){
                return x;
            }
        }
        throw new IllegalArgumentException("Unknow enum type: " + rank);
    }
}
