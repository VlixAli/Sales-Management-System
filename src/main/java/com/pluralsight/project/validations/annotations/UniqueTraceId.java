package com.pluralsight.project.validations.annotations;

import com.pluralsight.project.validations.UniqueTraceIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueTraceIdValidator.class)
public @interface UniqueTraceId {

    public String message() default  "Trace Id already exists" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
