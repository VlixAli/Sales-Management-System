package com.pluralsight.project.controllers;

import com.pluralsight.project.constants.StringConstants;
import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageActionRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping
    public ResponseEntity<Page<ActionResponse>> index(@Validated PageActionRequest request) {
        return ResponseEntity.ok(actionService.findAll(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionResponse> show(@PathVariable Long id) {
        ActionResponse actionResponse = actionService.findById(id);
        return ResponseEntity.ok(actionResponse);
    }

    @PostMapping
    public ResponseEntity<ActionResponse> store(@RequestBody @Validated(ActionRequest.Save.class) ActionRequest actionRequest) {
        ActionResponse actionResponse = actionService.create(actionRequest);
        return new ResponseEntity<>(actionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionResponse> update(@PathVariable Long id,
                                                 @RequestBody @Validated(ActionRequest.Update.class) ActionRequest actionRequest) {
        ActionResponse actionResponse = actionService.update(id, actionRequest);
        return ResponseEntity.ok(actionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        actionService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put(StringConstants.MESSAGE, StringConstants.ACTION_DELETED_SUCCESSFULLY);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
