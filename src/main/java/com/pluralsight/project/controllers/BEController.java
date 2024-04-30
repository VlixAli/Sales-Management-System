package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.BERequest;
import com.pluralsight.project.dtos.responses.BEResponse;
import com.pluralsight.project.services.BEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/bes")
@RequiredArgsConstructor
public class BEController {

    private final BEService beService;

    @GetMapping
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(beService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Validated BERequest request){
        BEResponse response = beService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BEResponse> update(@PathVariable Long id,
                                                      @RequestBody @Validated BERequest request){
        BEResponse response = beService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Long id){
        beService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Business entity deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
