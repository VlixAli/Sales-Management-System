package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.ApplicationExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ApplicationExistsValidator.class)
public @interface ApplicationExists {

    public String message() default  "No Application found with this id" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
