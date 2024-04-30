package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ApplicationRequest;
import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.mappers.ApplicationMapper;
import com.pluralsight.project.models.Application;
import com.pluralsight.project.repositories.ApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ApplicationResponse update(Long id,ApplicationRequest applicationRequest){
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isEmpty()){
            throw new EntityNotFoundException();
        }
        Application application = optionalApplication.get();
        application.setName(applicationRequest.getName());
        return applicationMapper.applicationToApplicationResponse(applicationRepository.save(application));
    }

    public void delete(Long id){
        if (applicationRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException();
        }
        applicationRepository.deleteById(id);
    }

}
