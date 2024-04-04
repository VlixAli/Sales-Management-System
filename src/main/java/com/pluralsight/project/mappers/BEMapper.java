package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.BEResponse;
import com.pluralsight.project.models.BE;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BEMapper {

    List<BEResponse> listBEResponse(List<BE> bes);

    BEResponse BEToBEResponse(BE be);

    BE BEResponseToBE(BEResponse beResponse);
}
