package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record BERequest(
        @NotBlank(message = "Business entity name must not be null or empty")
        String name
) {
}
