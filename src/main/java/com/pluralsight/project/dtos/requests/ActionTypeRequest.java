package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ActionTypeRequest {


    @NotBlank(message = "action type english name must not be null or empty", groups = Save.class)
    @NullOrNotBlank(message = "action type english name must not be empty", groups = Update.class)
    private String nameEn;

    @NotBlank(message = "action type arabic name must not be null or empty", groups = Save.class)
    @NullOrNotBlank(message = "action type arabic name must not be empty", groups = Update.class)
    private String nameAr;

    private String messageTempEn;
    private String messageTempAr;

    public interface Save {}

    public interface Update {}
}
