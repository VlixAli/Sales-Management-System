package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.UniqueTraceId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ActionRequest(
        String descriptionAr,
        String descriptionEn,
        @NotNull(message = "Trace Id must not be null", groups = Save.class)
        @Min(value = 1, message = "Trace Id must not be empty", groups = {Save.class, Update.class})
        @UniqueTraceId(groups = {Save.class, Update.class})
        String traceId,
        @NotNull(message = "User Id must not be null", groups = Save.class)
        @Min(value = 1, message = "User Id must not be empty", groups = {Save.class, Update.class})
        Long userId,
        @NotNull(message = "Action Type Id must not be null", groups = Save.class)
        @Min(value = 1, message = "Action Type Id must not be empty", groups = {Save.class, Update.class})
        Long actionTypeId,
        Long applicationId,
        Long beId,
        List<ParamRequest> params) {
    public interface Save {
    }

    public interface Update {
    }
}
