package com.pluralsight.project.controllers;

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

import java.util.Map;

@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping
    public ResponseEntity<Page<ActionResponse>> index(@Validated PageActionRequest request) {
        return ResponseEntity.ok(actionService.findAll(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionResponse> show(@PathVariable Long id) {
        return ResponseEntity.ok(actionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActionResponse> store(@RequestBody @Validated(ActionRequest.Save.class) ActionRequest actionRequest) {
        return new ResponseEntity<>(actionService.create(actionRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionResponse> update(@PathVariable Long id,
                                                 @RequestBody @Validated(ActionRequest.Update.class) ActionRequest actionRequest) {
        return ResponseEntity.ok(actionService.update(id, actionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        actionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
