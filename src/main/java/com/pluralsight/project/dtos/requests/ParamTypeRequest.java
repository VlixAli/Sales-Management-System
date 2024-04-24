package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.models.ParamType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ParamTypeRequest {
    @NotBlank(message = "English name shouldn't be null or empty")
    private String nameEn;

    @NotBlank(message = "Arabic name shouldn't be null or empty")
    private String nameAr;
}
