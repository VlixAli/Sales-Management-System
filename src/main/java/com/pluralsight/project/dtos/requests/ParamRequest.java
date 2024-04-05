package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.models.ParamType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamRequest {
    private String value;
    private Long paramType;
}
