package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.dtos.responses.ErrorResponse;
import com.pluralsight.project.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ResponseEntity<ErrorResponse> errorResponse =
            new ResponseEntity<>(new ErrorResponse("Action not found"), HttpStatus.NOT_FOUND);

    private final ActionService actionService;

    @GetMapping
    public ResponseEntity<List<ActionResponse>> index(@RequestParam(required = false) String username,
                                                      @RequestParam(required = false) String be) {
        return ResponseEntity.ok(actionService.findAll(username, be));
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        ActionResponse actionResponse = actionService.findById(id);
        if (actionResponse == null) {
            return errorResponse;
        }
        return ResponseEntity.ok(actionResponse);
    }

    @PostMapping
    public ResponseEntity store(@RequestBody ActionRequest actionRequest) {
        ActionResponse actionResponse = actionService.create(actionRequest);
        return new ResponseEntity<>(actionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ActionRequest actionRequest) {
        ActionResponse actionResponse = actionService.update(id, actionRequest);
        if (actionResponse == null) {
            return errorResponse;
        }
        return ResponseEntity.ok(actionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        actionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
