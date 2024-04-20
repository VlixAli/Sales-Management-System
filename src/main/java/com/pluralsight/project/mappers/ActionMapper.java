package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.models.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ActionMapper {

    public abstract List<ActionResponse> listActionResponse(List<Action> actions);

    @Mapping(target = "actionId", source = "action.id")
    public abstract ActionResponse actionToActionResponse(Action action);

    public Page<ActionResponse> pageActionResponse(Page<Action> actions){
        return actions.map(action -> actionToActionResponse(action));
    }


}
