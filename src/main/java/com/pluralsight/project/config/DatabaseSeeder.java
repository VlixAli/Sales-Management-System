package com.pluralsight.project.config;

import com.github.javafaker.Faker;
import com.pluralsight.project.models.*;
import com.pluralsight.project.models.enums.Role;
import com.pluralsight.project.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseSeeder implements WebMvcConfigurer {

    @Bean
    public CommandLineRunner dataLoader(
            ActionTypeRepository actionTypeRepository,
            ApplicationRepository applicationRepository,
            BERepository beRepository,
            ParamTypeRepository paramTypeRepository,
            UserRepository userRepository,
            ActionRepository actionRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Faker faker = new Faker();

            ActionType orderCreated = ActionType.builder()
                    .nameAr("طلب تم انشاءه")
                    .nameEn("ORDER_CREATED")
                    .messageTempAr("العميل {{customer.value}} قام بانشاء طلب {{order.value}} لشراء المنتج {{product.value}}")
                    .messageTempEn("customer {{customer.value}} created order {{order.value}} to buy product {{product.value}}")
                    .build();

            ActionType orderRefunded = ActionType.builder()
                    .nameAr("طلب مردود")
                    .nameEn("ORDER_REFUNDED")
                    .messageTempAr(" المستخدم {{user.value}} قام برد الطلب {{order.value}} الذي تم انشائه بواسطة العميل: {{customer.value}}")
                    .messageTempEn("User {{user.value}} refunded order {{order.value}} created by customer: {{customer.value}}")
                    .build();

            User jerry = User.builder()
                    .firstName("jerry")
                    .lastName("seinfeld")
                    .email("jerry@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.USER)
                    .build();

            User george = User.builder()
                    .firstName("george")
                    .lastName("costanza")
                    .email("george@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.USER)
                    .build();

            User kramer = User.builder()
                    .firstName("cosmo")
                    .lastName("kramer")
                    .email("kramer@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.USER)
                    .build();

            ParamType order = ParamType.builder()
                    .nameAr("طلب")
                    .nameEn("Order")
                    .build();

            ParamType customer = ParamType.builder()
                    .nameAr("عميل")
                    .nameEn("Customer")
                    .build();

            ParamType product = ParamType.builder()
                    .nameAr("منتج")
                    .nameEn("Product")
                    .build();

            Application tickets = Application.builder().name("tickets").build();
            Application bills = Application.builder().name("bills").build();

            BE fb = BE.builder().name("fb").build();
            BE gmc = BE.builder().name("gmc").build();

            applicationRepository.save(tickets);
            applicationRepository.save(bills);
            actionTypeRepository.save(orderCreated);
            actionTypeRepository.save(orderRefunded);
            userRepository.save(jerry);
            userRepository.save(george);
            userRepository.save(kramer);
            paramTypeRepository.save(order);
            paramTypeRepository.save(customer);
            paramTypeRepository.save(product);
            beRepository.save(fb);
            beRepository.save(gmc);

            List<Action> actions = new ArrayList<>();
            long traceId = 100;
            for (long i = 1; i <= 10; i++) {

                String name1 = faker.name().firstName();
                String product1 = faker.commerce().productName();
                Action action1 = Action.builder()
                        .descriptionAr(product1 + " لشراء المنتج " + traceId + "  قام بانشاء طلب " + name1 + "العميل ")
                        .descriptionEn("customer " + name1 + " created order " + traceId + " to buy product " + product1)
                        .actionTime(new Timestamp(System.currentTimeMillis()))
                        .traceId(Long.toString(traceId))
                        .user(jerry)
                        .actionType(orderCreated)
                        .application(tickets)
                        .be(fb)
                        .deleted(false)
                        .build();

                traceId++;

                String name2 = faker.name().firstName();
                Action action2 = Action.builder()
                        .descriptionAr( name2 + " الذي تم انشائه بواسطة العميل: " + traceId + " قام برد الطلب " + george.getFirstName() + " المستخدم ")
                        .descriptionEn(" User " + george.getFirstName() + " refunded order " + traceId + " created by customer: " + name2)
                        .actionTime(new Timestamp(System.currentTimeMillis()))
                        .traceId(Long.toString(traceId))
                        .user(george)
                        .actionType(orderRefunded)
                        .application(tickets)
                        .be(fb)
                        .deleted(false)
                        .build();

                traceId++;

                String name3 = faker.name().firstName();
                Action action3 = Action.builder()
                        .descriptionAr( name3 + " الذي تم انشائه بواسطة العميل: " + traceId + " قام برد الطلب " + jerry.getFirstName() + " المستخدم ")
                        .descriptionEn(" User " + jerry.getFirstName() + " refunded order " + traceId + " created by customer: " + name3)
                        .actionTime(new Timestamp(System.currentTimeMillis()))
                        .traceId(Long.toString(traceId))
                        .user(jerry)
                        .actionType(orderCreated)
                        .application(bills)
                        .be(gmc)
                        .deleted(false)
                        .build();

                traceId++;

                Param param1 = Param.builder()
                        .value(name1)
                        .action(action1)
                        .paramType(customer)
                        .build();
                Param param2 = Param.builder()
                        .value(product1)
                        .action(action1)
                        .paramType(product)
                        .build();
                Param param3 = Param.builder()
                        .value(faker.number().digit())
                        .action(action1)
                        .paramType(order)
                        .build();

                action1.setParams(List.of(param1, param2, param3));

                Param param4 = Param.builder()
                        .value(name2)
                        .action(action2)
                        .paramType(customer)
                        .build();
                Param param5 = Param.builder()
                        .value(faker.commerce().productName())
                        .action(action2)
                        .paramType(product)
                        .build();
                action2.setParams(List.of(param4, param5));

                Param param6 = Param.builder()
                        .value(name3)
                        .action(action3)
                        .paramType(customer)
                        .build();
                action3.setParams(List.of(param6));

                actions.add(action1);
                actions.add(action2);
                actions.add(action3);
            }
            actionRepository.saveAll(actions);


        };
    }
}
