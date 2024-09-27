package com.pluralsight.project.services;

import com.pluralsight.project.constants.StringConstants;
import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.mappers.ActionTypeMapper;
import com.pluralsight.project.models.ActionType;
import com.pluralsight.project.repositories.ActionTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionTypeService {

    private final ActionTypeRepository actionTypeRepository;
    private final ActionTypeMapper actionTypeMapper;

    public List<ActionTypeResponse> findAll() {
        return actionTypeMapper.listActionTypeResponse(actionTypeRepository.findAll());
    }

    public ActionTypeResponse create(ActionTypeRequest actionTypeRequest) {
        ActionType actionType = actionTypeMapper.actionTypeRequestToActionType(actionTypeRequest);
        actionTypeRepository.save(actionType);
        return actionTypeMapper.actionTypeToATResponse(actionType);
    }

    public ActionTypeResponse update(Long id, ActionTypeRequest actionTypeRequest) {
        ActionType actionType = actionTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND)
        );
        actionTypeMapper.updateActionTypeRequestToActionType(actionType, actionTypeRequest);
        return actionTypeMapper.actionTypeToATResponse(actionTypeRepository.save(actionType));
    }

    public void delete(Long id) {
        if (actionTypeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND);
        }
        actionTypeRepository.deleteById(id);
    }
}
