package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record LoginRequest(
        @NotNull
        @NotEmpty
        @Email(message = "Please enter a valid email address")
        String email,
        @NotNull
        @NotEmpty
        @Length(min = 6, message = "password must be greater than or equal to 6 characters")
        String password
) {
}
