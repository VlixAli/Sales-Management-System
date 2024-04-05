package com.pluralsight.project.repositories;


import com.pluralsight.project.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action,Long> {
    List<Action> findByBeName(String name);
    List<Action> findByUserLastName(String name);
    List<Action> findByUserLastNameAndBeName(String lastName,String BEName);
    List<Action> findByApplicationName(String name);
    List<Action> findByUserLastNameAndApplicationName(String lastName,String ApplicationNme);
    List<Action> findByUserLastNameAndApplicationNameAndBeName(String lastName,String ApplicationNme,String BEName);
    List<Action> findByActionTypeNameEn(String name);
    List<Action> findByUserLastNameAndActionTypeNameEn(String name,String ATName);
    List<Action> findByUserLastNameAndApplicationNameAndBeNameAndActionTypeNameEn(String lastName,String ApplicationNme,String BEName,String ActionTypeName);
    List<Action> findByParamsValue(String paramValue);
    List<Action> findByParamsValueAndParamsParamTypeNameEn(String value,String name);
    List<Action> findByUserLastNameAndApplicationNameAndBeNameAndActionTypeNameEnAndParamsValueAndParamsParamTypeNameEn(String lastName, String ApplicationNme, String BEName, String ActionTypeName,String ParamValue,String PTName);
    List<Action> findByUserLastNameAndParamsValueAndParamsParamTypeNameEn(String name,String PValue,String PTName);
    List<Action> findByUserLastNameAndActionTypeNameEnAndParamsValueAndParamsParamTypeNameEn(String name,String ATName,String PValue,String PTName);


}