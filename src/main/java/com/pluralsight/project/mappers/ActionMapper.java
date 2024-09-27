package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.models.Action;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public abstract class ActionMapper {

    @Mapping(target = "actionId", source = "action.id")
    public abstract ActionResponse actionToActionResponse(Action action);

    public Page<ActionResponse> pageActionToPageActionResponse(Page<Action> actions){
        return actions.map(this::actionToActionResponse);
    }
    @Mapping(target = "params", ignore = true)
   public abstract Action actionRequestToAction(ActionRequest actionRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "params", ignore = true)
    public abstract void updateActionRequestToAction(@MappingTarget Action action, ActionRequest actionRequest);
}
