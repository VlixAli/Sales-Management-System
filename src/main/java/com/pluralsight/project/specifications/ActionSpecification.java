package com.pluralsight.project.specifications;

import com.pluralsight.project.models.Action;
import com.pluralsight.project.models.Param;
import jakarta.persistence.criteria.Join;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class ActionSpecification {

    public static Specification<Action> hasUser(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("user").get("lastName"), "%" + username + "%");
    }

    public static Specification<Action> hasBE(String be) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("be").get("name"), "%" + be + "%");
    }

    public static Specification<Action> hasApplication(String application) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("application").get("name"), "%" + application + "%");
    }

    public static Specification<Action> hasAction(Long traceId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("traceId"), traceId);
    }

    public static Specification<Action> hasParam(String param) {
        return (root, query, criteriaBuilder) -> {
            Join<Param, Action> actionParams = root.join("params");
            return criteriaBuilder.like(actionParams.get("value"), "%" + param + "%");
        };
    }

    public static Specification<Action> hasParamType(String paramTypeEn) {
        return (root, query, criteriaBuilder) -> {
            Join<Param, Action> actionParams = root.join("params");
            return criteriaBuilder.equal(actionParams.get("paramType").get("nameEn"), paramTypeEn);
        };
    }


}
