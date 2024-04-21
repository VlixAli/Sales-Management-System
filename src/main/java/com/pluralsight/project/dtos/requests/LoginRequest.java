package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
public class LoginRequest {

    @NotNull
    @NotEmpty
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 6, message = "password must be greater than or equal to 6 characters")
    private String password;
}
