package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.models.ParamType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParamTypeMapper {

    ParamTypeResponse ParamTypeToPTResponse(ParamType paramType);

}
