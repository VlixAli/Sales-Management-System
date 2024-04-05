package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.ParamResponse;
import com.pluralsight.project.models.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParamMapper {

    List<ParamResponse> listParamResponse(List<Param> params);
    ParamResponse ParamToParamResponse(Param param);
    Param ParamResponseToParam(ParamResponse paramResponse);

}
