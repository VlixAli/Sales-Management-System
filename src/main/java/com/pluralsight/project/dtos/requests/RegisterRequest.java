package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "first name must not be null")
    @NotEmpty(message = "first name must not be empty")
    private String firstname;

    @NotNull(message = "last name must not be null")
    @NotEmpty(message = "last name must not be empty")
    private String lastname;

    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "You must provide a proper email format")
    private String email;

    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Password must not be empty")
    @Length(min = 6, message = "Password must be greater than or equal to 6 characters")
    private String password;
}
