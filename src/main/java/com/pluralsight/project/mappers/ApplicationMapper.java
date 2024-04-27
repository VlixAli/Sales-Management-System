package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ApplicationRequest;
import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.models.Application;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    List<ApplicationResponse> listApplicationResponse(List<Application> applications);

    ApplicationResponse applicationToApplicationResponse(Application application);

    Application applicationRequestToApplication(ApplicationRequest applicationRequest);

}
