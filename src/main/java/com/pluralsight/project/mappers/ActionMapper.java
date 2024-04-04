package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.models.Action;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionMapper {
    List<ActionResponse> listActionResponse(List<Action> actions);

    ActionResponse actionToActionResponse(Action action);

    Action actionRequestToAction(ActionRequest actionRequest);

    Action actionResponseToAction(ActionResponse actionResponse);

}
