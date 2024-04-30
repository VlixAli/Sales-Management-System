package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ParamTypeRequest;
import com.pluralsight.project.dtos.responses.ParamTypeResponse;
import com.pluralsight.project.exceptions.ResourceNotFoundException;
import com.pluralsight.project.mappers.ParamTypeMapper;
import com.pluralsight.project.models.ParamType;
import com.pluralsight.project.repositories.ParamTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParamTypeService {

    private final ParamTypeRepository paramTypeRepository;
    private final ParamTypeMapper paramTypeMapper;

    public List<ParamTypeResponse> findAll() {
        return paramTypeMapper.listParamTypeResponse(paramTypeRepository.findAll());
    }

    public ParamTypeResponse create(ParamTypeRequest paramTypeRequest) {
        ParamType paramType =
                paramTypeMapper.paramTypeRequestToParamType(paramTypeRequest);

        paramTypeRepository.save(paramType);
        return paramTypeMapper.paramTypeToPTResponse(paramType);
    }

    public ParamTypeResponse update(Long id,ParamTypeRequest paramTypeRequest){
        Optional<ParamType> optionalParamType = paramTypeRepository.findById(id);
        if (optionalParamType.isEmpty()){
            throw new ResourceNotFoundException("Param type");
        }
        ParamType paramType = optionalParamType.get();
        updateVariables(paramType, paramTypeRequest);
        return paramTypeMapper.paramTypeToPTResponse(paramTypeRepository.save(paramType));
    }

    public void delete(Long id){
        if (paramTypeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Param type");
        }
        paramTypeRepository.deleteById(id);
    }

    private void updateVariables(ParamType paramType , ParamTypeRequest request){
        if (request.getNameAr() != null){
            paramType.setNameAr(request.getNameAr());
        }
         if (request.getNameEn() != null){
            paramType.setNameEn(request.getNameEn());
        }

    }

}
