package com.pluralsight.project.repositories;

import com.pluralsight.project.models.Action;
import com.pluralsight.project.models.ActionType;
import com.pluralsight.project.models.User;
import com.pluralsight.project.models.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.Timestamp;

@DataJpaTest
class ActionRepositoryTest {

    @Autowired
    private ActionRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByTraceId() {
        //given
        User jerry = User.builder()
                .firstName("jerry")
                .lastName("seinfeld")
                .email("jerry@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();

        ActionType orderCreated = ActionType.builder()
                .nameAr("طلب تم انشاءه")
                .nameEn("ORDER_CREATED")
                .messageTempAr("العميل {{customer.value}} قام بانشاء طلب {{order.value}} لشراء المنتج {{product.value}}")
                .messageTempEn("customer {{customer.value}} created order {{order.value}} to buy product {{product.value}}")
                .build();

        userRepository.save(jerry);
        actionTypeRepository.save(orderCreated);

        Action action = Action.builder()
                .descriptionAr("وصف")
                .descriptionEn("description")
                .actionTime(new Timestamp(System.currentTimeMillis()))
                .traceId("1")
                .user(jerry)
                .actionType(orderCreated)
                .deleted(false)
                .build();

        underTest.save(action);

        //when
        Action action1 = underTest.findByTraceId("1").orElseThrow();

        assertThat(action).isEqualTo(action1);
    }
}