package com.kodilla.libraryapi.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RentCodeConstraintValidator implements ConstraintValidator<RentCode, String> {

    private String codePrefix;

    @Override
    public void initialize(RentCode code) {
        codePrefix = code.value();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext validatorContext) {

        if( code != null ) {
            return code.startsWith(codePrefix);
        } else {
            return true;
        }

    }

}
