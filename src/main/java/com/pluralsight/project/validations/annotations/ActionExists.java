package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.ActionExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ActionExistsValidator.class)
public @interface ActionExists {

    public String message() default  "No action found with this id" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
