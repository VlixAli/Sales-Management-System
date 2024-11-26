package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ApplicationRequest;
import com.pluralsight.project.dtos.responses.ApplicationResponse;
import com.pluralsight.project.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> index() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> store(@RequestBody @Validated ApplicationRequest applicationRequest) {
        return new ResponseEntity<>(applicationService.create(applicationRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> update(@PathVariable Long id,
                                                      @RequestBody @Validated ApplicationRequest request) {
        return ResponseEntity.ok(applicationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleted(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
