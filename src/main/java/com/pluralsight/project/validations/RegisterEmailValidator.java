package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.UserRepository;
import com.pluralsight.project.validations.annotations.RegisterEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterEmailValidator implements ConstraintValidator<RegisterEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
