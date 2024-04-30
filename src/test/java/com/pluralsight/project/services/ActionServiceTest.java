package com.pluralsight.project.services;

import com.pluralsight.project.dtos.requests.ActionRequest;
import com.pluralsight.project.dtos.requests.PageRequestDto;
import com.pluralsight.project.mappers.ActionMapper;
import com.pluralsight.project.models.Action;
import com.pluralsight.project.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActionServiceTest {

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


    private ActionService underTest;



    @BeforeEach
    void setUp() {
        underTest = new ActionService(actionMapper, actionRepository, userRepository,
                actionTypeRepository, applicationRepository, beRepository, paramTypeRepository );
    }

    @Test
    void findAll() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        Pageable pageable = new PageRequestDto().getPageable(pageRequestDto);

        //when
        underTest.findAll("jerry", "fb", "tickets", 1L,
                "param", "paramType", pageable);

        //then
        verify(actionMapper).pageActionResponse(actionRepository.findAll(pageable));
    }

    @Test
    @Disabled
    void findByIdUseActionMapper() {
        //when
        underTest.findById(1L);

        //then
        verify(actionMapper).actionToActionResponse(actionRepository.findById(1L).orElse(new Action()));
    }

    @Test
    void findById() {
        //when
        underTest.findById(1L);

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
        underTest.create(actionRequest);

        //then
        verify(actionRepository.save(action));

    }

    @Test
    void update() {

    }

    @Test
    void delete() {
        //when
        underTest.delete(1L);

        //then
        verify(actionRepository).deleteById(1L);
    }
}