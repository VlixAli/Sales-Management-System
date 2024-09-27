package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageActionRequest;
import com.pluralsight.project.dtos.responses.ActionResponse;
import com.pluralsight.project.mappers.ActionMapper;
import com.pluralsight.project.models.Action;
import com.pluralsight.project.repositories.*;
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


    @BeforeEach
    void setUp() {
        action = new Action();
    }

    @Test
    @SuppressWarnings("unchecked")
    void findAll_Success() {
        Page<Action> actions = new PageImpl<>(new ArrayList<>());
        Page<ActionResponse> actionResponses = new PageImpl<>(new ArrayList<>());
        PageActionRequest pageActionRequest = new PageActionRequest();
        when(actionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(actions);
        when(actionMapper.pageActionToPageActionResponse(actions)).thenReturn(actionResponses);

        //when
        Page<ActionResponse> response = actionService.findAll(pageActionRequest);

        //then
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
    @Disabled
    void findByIdUseActionMapper() {
        //when
        actionService.findById(1L);

        //then
        verify(actionMapper).actionToActionResponse(actionRepository.findById(1L).orElse(new Action()));
    }

    @Test
    void findById() {
        //when
        actionService.findById(1L);

        //then
        verify(actionRepository).findById(1L);
    }

    @Test
    @Disabled
    void create() {
        //given
        ActionRequest actionRequest = new ActionRequest();
        Action action = new Action();

        //when
        actionService.create(actionRequest);

        //then
        verify(actionRepository.save(action));

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