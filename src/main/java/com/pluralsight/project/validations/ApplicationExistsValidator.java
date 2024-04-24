package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.ApplicationRepository;
import com.pluralsight.project.validations.annotations.ApplicationExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationExistsValidator implements ConstraintValidator<ApplicationExists, Long> {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public boolean isValid(Long applicationId, ConstraintValidatorContext constraintValidatorContext) {
        return applicationId == null || applicationRepository.existsById(applicationId);
    }
}
