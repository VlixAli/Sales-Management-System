package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.RegisterEmail;
import com.pluralsight.project.validations.annotations.RegisterLastName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterRequest(
        @NotNull(message = "first name must not be null")
        @NotEmpty(message = "first name must not be empty")
        String firstname,
        @NotNull(message = "last name must not be null")
        @NotEmpty(message = "last name must not be empty")
        @RegisterLastName
        String lastname,
        @NotNull(message = "Email must not be null")
        @NotEmpty(message = "Email must not be empty")
        @Email(message = "You must provide a proper email format")
        @RegisterEmail
        String email,
        @NotNull(message = "Password must not be null")
        @NotEmpty(message = "Password must not be empty")
        @Length(min = 6, message = "Password must be greater than or equal to 6 characters")
        String password
) {
}
