package com.pluralsight.project.services;

import com.pluralsight.project.constants.StringConstants;
import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageActionRequest;
import com.pluralsight.project.dtos.requests.ParamRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.mappers.ActionMapper;
import com.pluralsight.project.models.Action;
import com.pluralsight.project.models.Param;
import com.pluralsight.project.repositories.*;
import com.pluralsight.project.utils.PageableMaker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.project.specifications.ActionSpecification.*;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionMapper actionMapper;
    private final ActionRepository actionRepository;
    private final UserRepository userRepository;
    private final ActionTypeRepository actionTypeRepository;
    private final ApplicationRepository applicationRepository;
    private final BERepository beRepository;
    private final ParamTypeRepository paramTypeRepository;

    public Page<ActionResponse> findAll(PageActionRequest request) {
        return actionMapper.pageActionToPageActionResponse(actionRepository.findAll(createFilters(request)
                , createPageable(request)));
    }

    public ActionResponse findById(Long id) {
        return actionMapper.actionToActionResponse(actionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.ACTION_NOT_FOUND)));
    }

    public ActionResponse create(ActionRequest actionRequest) {
        Action action = actionMapper.actionRequestToAction(actionRequest);
        mapIdsToTheirEntity(action, actionRequest);
        actionRepository.save(action);
        return actionMapper.actionToActionResponse(action);
    }

    public ActionResponse update(Long id, ActionRequest actionRequest) {
        Action action = actionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.ACTION_NOT_FOUND));
        actionMapper.updateActionRequestToAction(action, actionRequest);
        updateIdsToTheirEntities(action, actionRequest);
        return actionMapper.actionToActionResponse(actionRepository.save(action));
    }

    public void delete(Long id) {
        if (actionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(StringConstants.ACTION_NOT_FOUND);
        }
        actionRepository.deleteById(id);
    }


    private void mapIdsToTheirEntity(Action action, ActionRequest actionRequest) {
        action.setUser(userRepository.findById(actionRequest.userId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.USER_NOT_FOUND)
        ));
        action.setActionType(actionTypeRepository.findById(actionRequest.actionTypeId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND)
        ));
        action.setApplication(applicationRepository.findById(actionRequest.applicationId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.APPLICATION_NOT_FOUND)
        ));
        action.setBe(beRepository.findById(actionRequest.beId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.BUSINESS_ENTITY_NOT_FOUND)
        ));
        action.setParams(addParamsToAction(actionRequest.params(), action));
    }

    private List<Param> addParamsToAction(List<ParamRequest> paramRequests, Action action) {
        List<Param> params = new ArrayList<>();
        for (ParamRequest paramRequest : paramRequests) {
            Param param = new Param();
            param.setValue(paramRequest.value());
            param.setParamType(paramTypeRepository.findById(paramRequest.paramType()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.PARAM_TYPE_NOT_FOUND)
            ));
            param.setAction(action);
            params.add(param);
        }
        return params;
    }

    private void updateIdsToTheirEntities(Action action, ActionRequest actionRequest) {
        if (actionRequest.userId() != null) {
            action.setUser(userRepository.findById(actionRequest.userId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.USER_NOT_FOUND)
            ));
        }
        if (actionRequest.actionTypeId() != null) {
            action.setActionType(actionTypeRepository.findById(actionRequest.actionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND)
            ));
        }
        if (actionRequest.applicationId() != null) {
            action.setApplication(applicationRepository.findById(actionRequest.applicationId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.APPLICATION_NOT_FOUND)
            ));
        }
        if (actionRequest.beId() != null) {
            action.setBe(beRepository.findById(actionRequest.beId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.BUSINESS_ENTITY_NOT_FOUND)
            ));
        }
        if (actionRequest.params() != null) {
            action.setParams(addParamsToAction(actionRequest.params(), action));
        }
    }

    private Specification<Action> createFilters(PageActionRequest request) {
        return Specification.where(StringUtils.hasLength(request.username()) ? hasUser(request.username()) : null)
                .and(StringUtils.hasLength(request.be()) ? hasBE(request.be()) : null)
                .and(StringUtils.hasLength(request.application()) ? hasApplication(request.application()) : null)
                .and(request.traceId() != null ? hasAction(request.traceId()) : null)
                .and(StringUtils.hasLength(request.param()) ? hasParam(request.param()) : null)
                .and(StringUtils.hasLength(request.paramTypeEn()) ? hasParamType(request.paramTypeEn()) : null);
    }

    private Pageable createPageable(PageActionRequest request) {
        return PageableMaker.createPageable(request.pageNo(), request.sortDirection(), request.sortColumn());
    }
}
