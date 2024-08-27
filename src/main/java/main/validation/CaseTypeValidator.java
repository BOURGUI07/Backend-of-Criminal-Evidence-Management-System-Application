/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import main.util.enums.CaseType;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp
 */
@Component
public class CaseTypeValidator implements ConstraintValidator<ValidCaseType, String>{

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        var stringBelongsToEnumValues = Arrays.asList(CaseType.values()).stream().map(x->x.name()).anyMatch(y->y.equalsIgnoreCase(t));
        return (!t.isBlank()) && stringBelongsToEnumValues;
    }
    
}
