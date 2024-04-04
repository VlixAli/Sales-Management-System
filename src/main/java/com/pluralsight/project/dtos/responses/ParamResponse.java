package com.pluralsight.project.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamResponse {

    private Long paramId;
    private String paramValue ;
    private ParamTypeResponse paramType;


}

