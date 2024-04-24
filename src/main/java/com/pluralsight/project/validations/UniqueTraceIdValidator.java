package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.ActionRepository;
import com.pluralsight.project.validations.annotations.UniqueTraceId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueTraceIdValidator implements ConstraintValidator<UniqueTraceId, String> {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public boolean isValid(String traceId, ConstraintValidatorContext constraintValidatorContext) {
        return traceId == null || actionRepository.findByTraceId(traceId).isEmpty();
    }
}
