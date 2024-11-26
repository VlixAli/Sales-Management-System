package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.BERequest;
import com.pluralsight.project.dtos.responses.BEResponse;
import com.pluralsight.project.services.BEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bes")
@RequiredArgsConstructor
public class BEController {

    private final BEService beService;

    @GetMapping
    public ResponseEntity<List<BEResponse>> index() {
        return ResponseEntity.ok(beService.findAll());
    }

    @PostMapping
    public ResponseEntity<BEResponse> store(@RequestBody @Validated BERequest request) {
        return new ResponseEntity<>(beService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BEResponse> update(@PathVariable Long id, @RequestBody @Validated BERequest request) {
        return ResponseEntity.ok(beService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleted(@PathVariable Long id) {
        beService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
