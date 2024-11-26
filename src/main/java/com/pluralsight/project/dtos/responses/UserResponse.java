package com.pluralsight.project.dtos.responses;

public record UserResponse(
        Long id,
        String email,
        String lastName
) {
}
