package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.ActionTypeRepository;
import com.pluralsight.project.validations.annotations.ActionTypeExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionTypeExistsValidator implements ConstraintValidator<ActionTypeExists, Long> {

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    @Override
    public boolean isValid(Long actionTypeId, ConstraintValidatorContext constraintValidatorContext) {
        return actionTypeId == null || actionTypeRepository.existsById(actionTypeId);
    }
}
