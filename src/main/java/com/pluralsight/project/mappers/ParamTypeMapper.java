package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.requests.ParamTypeRequest;
import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.models.ParamType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParamTypeMapper {

    List<ParamTypeResponse> listParamTypeResponse(List<ParamType> paramTypes);

    ParamTypeResponse paramTypeToPTResponse(ParamType paramType);

    ParamType paramTypeRequestToParamType(ParamTypeRequest paramTypeRequest);

}
