package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.InValuesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InValuesValidator.class)
public @interface InValues {

    public String message() default  "Invalid value: It should be in the allowed values" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
