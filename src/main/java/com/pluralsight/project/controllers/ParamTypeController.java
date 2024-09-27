package com.pluralsight.project.controllers;

import com.pluralsight.project.constants.StringConstants;
import com.pluralsight.project.dtos.requests.ParamTypeRequest;
import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.services.ParamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/paramTypes")
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
        return new ResponseEntity<>(paramTypeService.update(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleted(@PathVariable Long id) {
        paramTypeService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put(StringConstants.MESSAGE, StringConstants.PARAM_TYPE_DELETED_SUCCESSFULLY);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
