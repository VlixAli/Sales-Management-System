package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ParamTypeRequest {

    @NotBlank(message = "English name shouldn't be null or empty", groups = Save.class)
    @NullOrNotBlank(message = "action type english name must not be empty",
            groups = Update.class)
    private String nameEn;

    @NotBlank(message = "Arabic name shouldn't be null or empty", groups = Save.class)
    @NullOrNotBlank(message = "action type english name must not be empty",
            groups = Update.class)
    private String nameAr;

    public interface Save {}

    public interface Update {}
}
