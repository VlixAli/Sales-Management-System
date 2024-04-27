package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ParamTypeRequest;
import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.models.ParamType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParamTypeMapper {

    ParamTypeResponse paramTypeToPTResponse(ParamType paramType);

    ParamType paramTypeRequestToParamType(ParamTypeRequest paramTypeRequest);

}
