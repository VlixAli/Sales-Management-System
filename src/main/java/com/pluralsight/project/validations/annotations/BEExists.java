package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.BEExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BEExistsValidator.class)
public @interface BEExists {

    public String message() default  "No BE found with this id" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
