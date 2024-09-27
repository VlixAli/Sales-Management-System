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


    private void mapIdsToTheirEntity(Action action,ActionRequest actionRequest) {
        action.setUser(userRepository.findById(actionRequest.getUserId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.USER_NOT_FOUND)
        ));
        action.setActionType(actionTypeRepository.findById(actionRequest.getActionTypeId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND)
        ));
        action.setApplication(applicationRepository.findById(actionRequest.getApplicationId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.APPLICATION_NOT_FOUND)
        ));
        action.setBe(beRepository.findById(actionRequest.getBeId()).orElseThrow(
                () -> new EntityNotFoundException(StringConstants.BE_NOT_FOUND)
        ));
        action.setParams(addParamsToAction(actionRequest.getParams(), action));
    }

    private List<Param> addParamsToAction(List<ParamRequest> paramRequests, Action action) {
        List<Param> params = new ArrayList<>();
        for (ParamRequest paramRequest : paramRequests) {
            Param param = new Param();
            param.setValue(paramRequest.getValue());
            param.setParamType(paramTypeRepository.findById(paramRequest.getParamType()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.PARAM_TYPE_NOT_FOUND)
            ));
            param.setAction(action);
            params.add(param);
        }
        return params;
    }

    private void updateIdsToTheirEntities(Action action, ActionRequest actionRequest) {
        if (actionRequest.getUserId() != null) {
            action.setUser(userRepository.findById(actionRequest.getUserId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.USER_NOT_FOUND)
            ));
        }
        if (actionRequest.getActionTypeId() != null) {
            action.setActionType(actionTypeRepository.findById(actionRequest.getActionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.ACTION_TYPE_NOT_FOUND)
            ));
        }
        if (actionRequest.getApplicationId() != null) {
            action.setApplication(applicationRepository.findById(actionRequest.getApplicationId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.APPLICATION_NOT_FOUND)
            ));
        }
        if (actionRequest.getBeId() != null) {
            action.setBe(beRepository.findById(actionRequest.getBeId()).orElseThrow(
                    () -> new EntityNotFoundException(StringConstants.BE_NOT_FOUND)
            ));
        }
        if (actionRequest.getParams() != null) {
            action.setParams(addParamsToAction(actionRequest.getParams(), action));
        }
    }

    private Specification<Action> createFilters(PageActionRequest request) {
        return Specification.where(StringUtils.hasLength(request.getUsername()) ? hasUser(request.getUsername()) : null)
                .and(StringUtils.hasLength(request.getBe()) ? hasBE(request.getBe()) : null)
                .and(StringUtils.hasLength(request.getApplication()) ? hasApplication(request.getApplication()) : null)
                .and(request.getTraceId() != null ? hasAction(request.getTraceId()) : null)
                .and(StringUtils.hasLength(request.getParam()) ? hasParam(request.getParam()) : null)
                .and(StringUtils.hasLength(request.getParamTypeEn()) ? hasParamType(request.getParamTypeEn()) : null);
    }

    private Pageable createPageable(PageActionRequest request) {
        return PageableMaker.createPageable(request.getPageNo(), request.getSortDirection(), request.getSortColumn());
    }
}
