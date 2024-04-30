package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ApplicationRequest;
import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(applicationService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Validated ApplicationRequest applicationRequest){
        ApplicationResponse applicationResponse = applicationService.create(applicationRequest);
        return new ResponseEntity<>(applicationResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> update(@PathVariable Long id,
                                    @RequestBody @Validated ApplicationRequest request){
        ApplicationResponse response = applicationService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Long id){
        applicationService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "application deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
