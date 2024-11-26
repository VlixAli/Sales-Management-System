package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ParamTypeRequest;
import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.services.ParamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paramTypes")
@RequiredArgsConstructor
public class ParamTypeController {

    private final ParamTypeService paramTypeService;

    @GetMapping
    public ResponseEntity<List<ParamTypeResponse>> index() {
        return ResponseEntity.ok(paramTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<ParamTypeResponse> store(@RequestBody @Validated(ParamTypeRequest.Save.class) ParamTypeRequest request) {
        return new ResponseEntity<>(paramTypeService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParamTypeResponse> update(
            @PathVariable Long id,
            @RequestBody @Validated(ParamTypeRequest.Update.class) ParamTypeRequest request) {
        return ResponseEntity.ok(paramTypeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleted(@PathVariable Long id) {
        paramTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
