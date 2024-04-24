package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.RegisterEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RegisterEmailValidator.class)
public @interface RegisterEmail {
    public String message() default  "User already exists" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
