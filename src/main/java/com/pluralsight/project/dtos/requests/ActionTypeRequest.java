package com.pluralsight.project.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActionTypeRequest {


    @NotBlank(message = "action type english name must not be null or empty")
    private String nameEn;

    @NotBlank(message = "action type arabic name must not be null or empty")
    private String nameAr;

    private String messageTempEn;
    private String messageTempAr;
}
