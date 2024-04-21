package com.pluralsight.project.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    public Long id ;

    private String email;

    private String lastName;


}
