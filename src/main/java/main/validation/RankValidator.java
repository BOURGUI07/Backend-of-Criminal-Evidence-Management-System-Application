/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import main.util.enums.Rank;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp
 */
@Component
public class RankValidator implements ConstraintValidator<ValidRank, String>{

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        var stringBelongsToEnumValues = Arrays.asList(Rank.values()).stream().map(x->x.name()).anyMatch(y->y.equalsIgnoreCase(t));
        return (!t.isBlank()) && stringBelongsToEnumValues;
    }
    
}
