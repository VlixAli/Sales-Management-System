package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.UserRepository;
import com.pluralsight.project.validations.annotations.RegisterEmail;
import com.pluralsight.project.validations.annotations.RegisterLastName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterLastNameValidator implements ConstraintValidator<RegisterLastName, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByLastName(email).isEmpty();
    }
}
