package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.ActionTypeExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ActionTypeExistsValidator.class)
public @interface ActionTypeExists {

    public String message() default  "no Action type found with this id" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
