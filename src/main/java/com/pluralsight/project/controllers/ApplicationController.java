package com.pluralsight.project.controllers;

import com.pluralsight.project.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(applicationService.findAll());
    }
}
