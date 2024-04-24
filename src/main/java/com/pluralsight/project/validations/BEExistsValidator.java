package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.BERepository;
import com.pluralsight.project.validations.annotations.BEExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BEExistsValidator implements ConstraintValidator<BEExists, Long> {

    @Autowired
    private BERepository beRepository;

    @Override
    public boolean isValid(Long beId, ConstraintValidatorContext constraintValidatorContext) {
        return beId == null || beRepository.existsById(beId);
    }
}
