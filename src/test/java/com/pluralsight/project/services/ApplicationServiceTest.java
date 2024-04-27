package com.pluralsight.project.services;

import com.pluralsight.project.mappers.ApplicationMapper;
import com.pluralsight.project.repositories.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationMapper applicationMapper;

    private ApplicationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ApplicationService(applicationRepository, applicationMapper);
    }

    @Test
    void findAllUsesApplicationRepositoryFindAll() {
        //when
        underTest.findAll();

        //then
        verify(applicationRepository).findAll();
    }

    @Test
    void findAllUsesApplicationMapperListApplicationResponse(){
        //when
        underTest.findAll();

        //then
        verify(applicationMapper).listApplicationResponse(new ArrayList<>());
    }


}