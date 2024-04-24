package com.pluralsight.project.validations;

import com.pluralsight.project.repositories.UserRepository;
import com.pluralsight.project.validations.annotations.UserExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistsValidator implements ConstraintValidator<UserExists, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        return userId == null || userRepository.findById(userId).isPresent();
    }
}
