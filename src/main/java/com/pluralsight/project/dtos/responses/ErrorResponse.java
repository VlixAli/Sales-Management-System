package com.pluralsight.project.dtos.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private String status = "failed";
    private final String message;
}
