package com.pluralsight.project.services;

import com.pluralsight.project.constants.StringConstants;
import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageActionRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.mappers.ActionMapper;
import com.pluralsight.project.models.Action;
import com.pluralsight.project.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class ActionServiceTests {

    @Mock
    private ActionRepository actionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ActionTypeRepository actionTypeRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private BERepository beRepository;

    @Mock
    private ParamTypeRepository paramTypeRepository;

    @Mock
    private ActionMapper actionMapper;

    @InjectMocks
    private ActionService actionService;

    Action action;
    ActionRequest actionRequest;
    ActionResponse actionResponse;

    @BeforeEach
    void setUp() {
        action = new Action();
        actionRequest = new ActionRequest();
        actionResponse = new ActionResponse();
    }

    @Test
    @SuppressWarnings("unchecked")
    void findAll_ReturnPageOFAllActions() {
        Page<Action> actions = new PageImpl<>(new ArrayList<>());
        Page<ActionResponse> actionResponses = new PageImpl<>(new ArrayList<>());
        PageActionRequest pageActionRequest = new PageActionRequest();
        when(actionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(actions);
        when(actionMapper.pageActionToPageActionResponse(actions)).thenReturn(actionResponses);

        Page<ActionResponse> response = actionService.findAll(pageActionRequest);

        verify(actionRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
        verify(actionMapper, times(1)).pageActionToPageActionResponse(actions);
        assertNotNull(response);
        assertAll(
                () -> assertEquals(actionResponses.getContent(), response.getContent()),
                () -> assertEquals(actionResponses.getSize(), response.getSize()),
                () -> assertEquals(actionResponses.getNumber(), response.getNumber()),
                () -> assertEquals(actionResponses.getTotalElements(), response.getTotalElements()),
                () -> assertEquals(actionResponses.getTotalPages(), response.getTotalPages()),
                () -> assertEquals(actionResponses.hasNext(), response.hasNext())
        );
    }

    @Test
    void findById_existingAction_returnsAction() {
        when(actionRepository.findById(1L)).thenReturn(Optional.of(action));
        when(actionMapper.actionToActionResponse(action)).thenReturn(actionResponse);
        ActionResponse result = actionService.findById(1L);
        assertNotNull(result);
        assertEquals(actionResponse, result);
    }

    @Test
    void findById_nonExistingAction_ThrowsEntityNotFoundException() {
        when(actionRepository.findById(1L)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> actionService.findById(1L));
        verify(actionRepository, times(1)).findById(1L);
        assertEquals(StringConstants.ACTION_NOT_FOUND, exception.getMessage());
    }

    @Test
    void createAction_returnsActionResponse() {
        when(actionMapper.actionRequestToAction(actionRequest)).thenReturn(action);
        when(actionRepository.save(action)).thenReturn(action);
        when(actionMapper.actionToActionResponse(action)).thenReturn(actionResponse);
        ActionResponse result = actionService.create(actionRequest);
        assertNotNull(result);
        assertEquals(actionResponse, result);
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
        //when
        actionService.delete(1L);

        //then
        verify(actionRepository).deleteById(1L);
    }
}