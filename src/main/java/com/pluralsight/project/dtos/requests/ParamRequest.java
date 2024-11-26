package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record ParamRequest(
        @NotBlank(message = "value shouldn't be null or empty")
        String value,
        @NotBlank(message = "action shouldn't be null or empty")
        Long action,
        @NotBlank(message = "param type shouldn't be null or empty")
        Long paramType
) {
}
