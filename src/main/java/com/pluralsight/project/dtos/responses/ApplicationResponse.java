package com.pluralsight.project.dtos.responses;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationResponse {

    private Long applicationId;
    private String applicationName;


}
