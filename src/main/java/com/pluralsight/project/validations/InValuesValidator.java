package com.pluralsight.project.validations;

import com.pluralsight.project.validations.annotations.InValues;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InValuesValidator implements ConstraintValidator<InValues, String> {

    private final List<String> values = new ArrayList<>();

    @Override
    public void initialize(InValues constraintAnnotation) {
        values.addAll(Arrays.asList(constraintAnnotation.value()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return values.contains(value) || value == null;
    }
}
