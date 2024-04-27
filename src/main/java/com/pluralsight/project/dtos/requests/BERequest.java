package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BERequest {

    @NotBlank(message = "Business entity name must not be null or empty")
    private String name;
}
