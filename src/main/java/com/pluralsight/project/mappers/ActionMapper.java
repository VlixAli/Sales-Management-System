package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.models.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    List<ActionResponse> listActionResponse(List<Action> actions);

    @Mapping(target = "actionId", source = "action.id")
    ActionResponse actionToActionResponse(Action action);


}
