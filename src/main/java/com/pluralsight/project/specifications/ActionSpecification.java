package com.pluralsight.project.specifications;

import com.pluralsight.project.models.Action;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class ActionSpecification {

    public static Specification<Action> hasUser(String username) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("user").get("lastName"), "%" + username + "%"));
    }

    public static Specification<Action> hasBE(String be) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("be").get("name"), "%" + be + "%"));
    }


}
