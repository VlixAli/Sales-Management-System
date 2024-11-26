package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.InValues;

public record PageActionRequest(
        String username,
        String be,
        String application,
        Long traceId,
        String param,
        String paramTypeEn,
        Integer pageNo,
        @InValues(value = {"ASC", "DESC"}, message = "the value must be either ASC or DESC")
        String sortDirection,
        @InValues(value = {"id", "descriptionAr", "descriptionEn", "actionTime", "traceId"},
                message = "the value must be in [id, descriptionAr, descriptionEn, actionTime, traceId]")
        String sortColumn
) {
}
