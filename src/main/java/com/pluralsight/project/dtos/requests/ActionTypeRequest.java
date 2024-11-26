package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;

public record ActionTypeRequest(
        @NotBlank(message = "action type english name must not be null or empty", groups = Save.class)
        @NullOrNotBlank(message = "action type english name must not be empty", groups = Update.class)
        String nameEn,
        @NotBlank(message = "action type arabic name must not be null or empty", groups = Save.class)
        @NullOrNotBlank(message = "action type arabic name must not be empty", groups = Update.class)
        String nameAr,
        String messageTempEn,
        String messageTempAr) {
    public interface Save {
    }

    public interface Update {
    }
}
