package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.ActionExists;
import com.pluralsight.project.validations.annotations.ParamTypeExists;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamRequest {

    @NotBlank(message = "value shouldn't be null or empty")
    private String value;

    @ActionExists
    @NotBlank(message = "action shouldn't be null or empty")
    private Long action;

    @ParamTypeExists
    @NotBlank(message = "param type shouldn't be null or empty")
    private Long paramType;
}
