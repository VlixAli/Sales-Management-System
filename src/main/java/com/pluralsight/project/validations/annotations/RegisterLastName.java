package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.RegisterEmailValidator;
import com.pluralsight.project.validations.RegisterLastNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RegisterLastNameValidator.class)
public @interface RegisterLastName {
    public String message() default  "last name is taken" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
