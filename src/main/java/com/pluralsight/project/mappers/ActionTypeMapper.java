package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ActionTypeRequest;
import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.models.ActionType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionTypeMapper {

    List<ActionTypeResponse> listActionTypeResponse(List<ActionType> actionTypes);

    ActionTypeResponse actionTypeToATResponse(ActionType actionType);

    ActionType actionTypeRequestToActionType(ActionTypeRequest actionTypeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActionTypeRequestToActionType(@MappingTarget ActionType actionType, ActionTypeRequest actionTypeRequest);
}
