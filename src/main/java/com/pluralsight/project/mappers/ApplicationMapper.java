package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.models.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationResponse ApplicationToApplicationResponse(Application application);

}
