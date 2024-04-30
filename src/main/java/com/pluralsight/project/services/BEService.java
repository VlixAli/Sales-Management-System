package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.BERequest;
import com.pluralsight.project.dtos.responses.BEResponse;
import com.pluralsight.project.mappers.BEMapper;
import com.pluralsight.project.models.BE;
import com.pluralsight.project.repositories.BERepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BEService {

    private final BERepository beRepository;
    private final BEMapper beMapper;

    public List<BEResponse> findAll() {
        return beMapper.listBEResponse(beRepository.findAll());
    }

    public BEResponse create(BERequest beRequest) {
        BE be =
                beMapper.BERequestToBE(beRequest);

        beRepository.save(be);
        return beMapper.BEToBEResponse(be);
    }

    public BEResponse update(Long id,BERequest beRequest){
        Optional<BE> optionalBE = beRepository.findById(id);
        if (optionalBE.isEmpty()){
            throw new EntityNotFoundException();
        }
        BE be = optionalBE.get();
        be.setName(beRequest.getName());
        return beMapper.BEToBEResponse(beRepository.save(be));
    }

    public void delete(Long id){
        if (beRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException();
        }
        beRepository.deleteById(id);
    }

}
