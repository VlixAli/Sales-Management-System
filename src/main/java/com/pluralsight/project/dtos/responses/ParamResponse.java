package com.pluralsight.project.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamResponse {

    private Long id;
    private String value ;
    private ParamTypeResponse paramType;


}

