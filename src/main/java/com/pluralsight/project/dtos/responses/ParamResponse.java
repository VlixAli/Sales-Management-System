package com.pluralsight.project.dtos.responses;

public record ParamResponse(
        Long id,
        String value,
        ParamTypeResponse paramType
) {
}

