package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.ParamTypeRepository;
import com.pluralsight.project.validations.annotations.ParamTypeExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParamTypeExistsValidator implements ConstraintValidator<ParamTypeExists, Long> {

    @Autowired
    private ParamTypeRepository paramTypeRepository;

    @Override
    public boolean isValid(Long paramTypeId, ConstraintValidatorContext constraintValidatorContext) {
        return paramTypeRepository.existsById(paramTypeId);
    }
}
