package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ActionTypeResponse;
import com.pluralsight.project.models.ActionType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionTypeMapper {

    ActionTypeResponse ActionTypeToATResponse(ActionType actionType);
}
