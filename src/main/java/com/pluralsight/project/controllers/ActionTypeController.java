package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.services.ActionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/actionTypes")
@RequiredArgsConstructor
public class ActionTypeController {

    private final ActionTypeService actionTypeService;

    @GetMapping
    public ResponseEntity<List<ActionTypeResponse>> index() {
        return ResponseEntity.ok(actionTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<ActionTypeResponse> store(@RequestBody @Validated(ActionTypeRequest.Save.class) ActionTypeRequest request) {
        return new ResponseEntity<>(actionTypeService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionTypeResponse> update(
            @PathVariable Long id,
            @RequestBody @Validated(ActionTypeRequest.Update.class) ActionTypeRequest request) {
        return ResponseEntity.ok(actionTypeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleted(@PathVariable Long id) {
        actionTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
