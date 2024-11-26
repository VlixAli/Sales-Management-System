package com.pluralsight.project.dtos.responses;

import java.util.Date;
import java.util.List;

public record ActionResponse(
        Long actionId,
        String descriptionAr,
        String descriptionEn,
        Date actionTime,
        String traceId,
        UserResponse user,
        ActionTypeResponse actionType,
        ApplicationResponse application,
        BEResponse be,
        List<ParamResponse> params

) {
}
