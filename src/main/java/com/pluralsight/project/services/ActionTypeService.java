package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.mappers.ActionTypeMapper;
import com.pluralsight.project.models.ActionType;
import com.pluralsight.project.repositories.ActionTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionTypeService {

    private final ActionTypeRepository actionTypeRepository;
    private final ActionTypeMapper actionTypeMapper;

    public List<ActionTypeResponse> findAll() {
        return actionTypeMapper.listActionTypeResponse(actionTypeRepository.findAll());
    }

    public ActionTypeResponse create(ActionTypeRequest actionTypeRequest) {
        ActionType actionType =
                actionTypeMapper.actionTypeRequestToActionType(actionTypeRequest);

        actionTypeRepository.save(actionType);
        return actionTypeMapper.actionTypeToATResponse(actionType);
    }

    public ActionTypeResponse update(Long id, ActionTypeRequest actionTypeRequest) {
        Optional<ActionType> optionalActionType = actionTypeRepository.findById(id);
        if (optionalActionType.isEmpty()) {
            throw new EntityNotFoundException();
        }
        ActionType actionType = optionalActionType.get();
        updateVariables(actionType, actionTypeRequest);
        return actionTypeMapper.actionTypeToATResponse(actionTypeRepository.save(actionType));
    }

    public void delete(Long id) {
        if (actionTypeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        actionTypeRepository.deleteById(id);
    }

    private void updateVariables(ActionType actionType, ActionTypeRequest request) {
        if (request.getNameAr() != null) {
            actionType.setNameAr(request.getNameAr());
        }
        if (request.getNameEn() != null) {
            actionType.setNameEn(request.getNameEn());
        }
        if (request.getMessageTempAr() != null) {
            actionType.setMessageTempAr(request.getMessageTempAr());
        }
        if (request.getMessageTempEn() != null) {
            actionType.setMessageTempEn(request.getMessageTempEn());
        }

    }

}
