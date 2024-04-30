package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.services.ActionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/actionTypes")
@RequiredArgsConstructor
public class ActionTypeController {

    private final ActionTypeService ActionTypeService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(ActionTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> store(
            @RequestBody @Validated(ActionTypeRequest.Save.class) ActionTypeRequest request) {
        ActionTypeResponse response = ActionTypeService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionTypeResponse> update(
            @PathVariable Long id,
            @RequestBody @Validated(ActionTypeRequest.Update.class) ActionTypeRequest request) {
        ActionTypeResponse response = ActionTypeService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Long id) {
        ActionTypeService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Action type deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
