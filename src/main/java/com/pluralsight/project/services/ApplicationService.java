package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ApplicationRequest;
import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.mappers.ApplicationMapper;
import com.pluralsight.project.models.Application;
import com.pluralsight.project.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public List<ApplicationResponse> findAll() {
        return applicationMapper.listApplicationResponse(applicationRepository.findAll());
    }

    public ApplicationResponse create(ApplicationRequest applicationRequest) {
        Application application =
                applicationMapper.applicationRequestToApplication(applicationRequest);

        applicationRepository.save(application);
        return applicationMapper.applicationToApplicationResponse(application);
    }

}
