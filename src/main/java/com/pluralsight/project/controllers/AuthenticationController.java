package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.LoginRequest;
import com.pluralsight.project.dtos.requests.RegisterRequest;
import com.pluralsight.project.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterRequest request,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("\n")));
        }
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            return new ResponseEntity<>("something is wrong with your data", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest request,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("\n")));
        }
        try {
            return ResponseEntity.ok(authenticationService.login(request));
        } catch (Exception e) {
            return new ResponseEntity<>("wrong credentials", HttpStatus.FORBIDDEN);
        }
    }
}
