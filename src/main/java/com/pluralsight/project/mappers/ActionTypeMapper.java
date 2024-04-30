package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.models.ActionType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionTypeMapper {

    List<ActionTypeResponse> listActionTypeResponse(List<ActionType> actionTypes);

    ActionTypeResponse actionTypeToATResponse(ActionType actionType);

    ActionType actionTypeRequestToActionType(ActionTypeRequest actionTypeRequest);
}
