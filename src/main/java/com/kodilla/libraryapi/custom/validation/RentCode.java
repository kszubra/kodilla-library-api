package com.kodilla.libraryapi.custom.validation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RentCodeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RentCode {

    public String value() default "defaultValue";
    public String message() default "wrong";

    // mandatory to avoid ConstraintDefinitionException: HV000074
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
