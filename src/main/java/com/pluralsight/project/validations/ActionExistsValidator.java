package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.ActionRepository;
import com.pluralsight.project.validations.annotations.ActionExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionExistsValidator implements ConstraintValidator<ActionExists, Long> {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public boolean isValid(Long actionId, ConstraintValidatorContext constraintValidatorContext) {
        return actionRepository.existsById(actionId);
    }
}
