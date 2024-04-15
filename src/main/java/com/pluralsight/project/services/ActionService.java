package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.ParamRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.mappers.ActionMapper;
import com.pluralsight.project.models.Action;
import com.pluralsight.project.models.Param;
import com.pluralsight.project.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ActionResponse> findAll(String username, String be, String application, Long traceId
            , String param) {
        Specification<Action> filters =
                Specification.where(StringUtils.hasLength(username) ? hasUser(username) : null)
                        .and(StringUtils.hasLength(be) ? hasBE(be) : null)
                        .and(StringUtils.hasLength(application) ? hasApplication(application) : null)
                        .and(traceId != null ? hasAction(traceId) : null)
                        .and(StringUtils.hasLength(param) ? hasParam(param) : null);

        return actionMapper.listActionResponse(actionRepository.findAll(filters));
    }

    public ActionResponse findById(Long id) {
        Optional<Action> action = actionRepository.findById(id);
        if (action.isEmpty()) {
            return null;
        }
        return actionMapper.actionToActionResponse(action.get());
    }

    public ActionResponse create(ActionRequest actionRequest) {
        Action action = actionRequestToAction(actionRequest);
        actionRepository.save(action);
        return actionMapper.actionToActionResponse(action);
    }

    public ActionResponse update(Long id, ActionRequest actionRequest) {
        Optional<Action> existingAction = actionRepository.findById(id);
        if (existingAction.isEmpty()) {
            return null;
        }
        Action action = existingAction.get();
        updateActionRequestToAction(action, actionRequest);

        return actionMapper.actionToActionResponse(actionRepository.save(action));
    }

    public void delete(Long id) {
        actionRepository.deleteById(id);
    }


    private Action actionRequestToAction(ActionRequest actionRequest) {
        Action action = new Action();
        action.setDescriptionAr(actionRequest.getDescriptionAr());
        action.setDescriptionEn(actionRequest.getDescriptionEn());
        action.setTraceId(actionRequest.getTraceId());
        action.setUser(userRepository.findById(actionRequest.getUser()).get());
        action.setActionType(actionTypeRepository.findById(actionRequest.getActionType()).get());
        action.setApplication(applicationRepository.findById(actionRequest.getApplication()).get());
        action.setBe(beRepository.findById(actionRequest.getBe()).get());
        action.setParams(addParamsToAction(actionRequest.getParams(), action));

        return action;
    }

    private List<Param> addParamsToAction(List<ParamRequest> paramRequests, Action action) {
        List<Param> params = new ArrayList<>();
        for (ParamRequest paramRequest : paramRequests) {
            Param param = new Param();
            param.setValue(paramRequest.getValue());
            param.setParamType(paramTypeRepository.findById(paramRequest.getParamType()).get());
            param.setAction(action);
            params.add(param);
        }
        return params;
    }

    private void updateActionRequestToAction(Action action, ActionRequest actionRequest) {
        if (actionRequest.getDescriptionAr() != null) {
            action.setDescriptionAr(actionRequest.getDescriptionAr());
        }
        if (actionRequest.getDescriptionEn() != null) {
            action.setDescriptionEn(actionRequest.getDescriptionEn());
        }
        if (actionRequest.getTraceId() != null) {
            action.setTraceId(actionRequest.getTraceId());
        }
        if (actionRequest.getUser() != null) {
            action.setUser(userRepository.findById(actionRequest.getUser()).get());
        }
        if (actionRequest.getActionType() != null) {
            action.setActionType(actionTypeRepository.findById(actionRequest.getActionType()).get());
        }
        if (actionRequest.getApplication() != null) {
            action.setApplication(applicationRepository.findById(actionRequest.getApplication()).get());
        }
        if (actionRequest.getBe() != null) {
            action.setBe(beRepository.findById(actionRequest.getBe()).get());
        }
        if (actionRequest.getParams() != null) {
            action.setParams(addParamsToAction(actionRequest.getParams(), action));
        }
    }
}
