package com.pluralsight.project.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        @JsonProperty("access token")
        String accessToken,
        @JsonProperty("refresh token")
        String refreshToken
) {
}
