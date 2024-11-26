package com.pluralsight.project.dtos.responses;

import com.pluralsight.project.constants.StringConstants;

public record ErrorResponse(String status,
                            String message) {
    public ErrorResponse(String message) {
        this(StringConstants.FAILED, message);
    }
}
