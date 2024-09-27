package com.pluralsight.project.dtos.responses;

import com.pluralsight.project.constants.StringConstants;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private String status = StringConstants.FAILED;
    private final String message;
}
