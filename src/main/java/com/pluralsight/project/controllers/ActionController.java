package com.pluralsight.project.controllers;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageActionRequest;
import com.pluralsight.project.dtos.requests.PageRequestDto;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.dtos.responses.ErrorResponse;
import com.pluralsight.project.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ResponseEntity<ErrorResponse> errorResponse =
            new ResponseEntity<>(new ErrorResponse("Action not found"), HttpStatus.NOT_FOUND);

    private final ActionService actionService;

    @GetMapping
    public ResponseEntity<Page<ActionResponse>> index(@Validated PageActionRequest request) {
        PageRequestDto pageRequestDto = new PageRequestDto(request.getPageNo(),
                request.sortToDirection(request.getSort()), request.getSortByColumn());

        Pageable pageable = new PageRequestDto().getPageable(pageRequestDto);

        return ResponseEntity.ok(actionService.findAll(request.getUsername(), request.getBe(),
                request.getApplication(), request.getTraceId(), request.getParam(),
                request.getParamTypeEn(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        ActionResponse actionResponse = actionService.findById(id);
        if (actionResponse == null) {
            return errorResponse;
        }
        return ResponseEntity.ok(actionResponse);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Validated(ActionRequest.Save.class) ActionRequest actionRequest) {
        ActionResponse actionResponse = actionService.create(actionRequest);
        return new ResponseEntity<>(actionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                 @RequestBody @Validated(ActionRequest.Update.class) ActionRequest actionRequest) {
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
