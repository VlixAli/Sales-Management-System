package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.models.ParamType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ParamTypeRequest {
    private String paramId ;
    private String paramValue ;
    private ActionRequest action;
    private ParamType paramType;

}
