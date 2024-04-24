package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.models.ParamType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamRequest {
    private String value;

    @ActionExists
    @NotBlank(message = "action shouldn't be null or empty")
    private Long action;


    private Long paramType;
}
