package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record ApplicationRequest(
        @NotBlank(message = "application name must not be null or empty") String name) {
}
