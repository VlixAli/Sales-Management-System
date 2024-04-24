package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.ParamTypeExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ParamTypeExistsValidator.class)
public @interface ParamTypeExists {

    public String message() default  "No param type found with this id" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
