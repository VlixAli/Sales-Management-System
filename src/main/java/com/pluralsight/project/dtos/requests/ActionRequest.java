package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.dtos.responses.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class ActionRequest {
    private String descriptionAr ;
    private String descriptionEn ;
    private String traceId;
    private UserResponse user;
    private ActionTypeResponse actionType;
    private ApplicationResponse application;
    private BEResponse be;
    private List<ParamResponse> params;

}
