package com.pluralsight.project.repositories;


import com.pluralsight.project.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {

}
