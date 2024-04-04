package com.pluralsight.project.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ActionResponse {
    private Long actionId ;
    private String descriptionAr ;
    private String descriptionEn ;
    private Date actionTime;
    private String traceId;
    private UserResponse user;
    private ActionTypeResponse actionType;
    private ApplicationResponse application;
    private BEResponse be;
    private List<ParamResponse> params;

}
