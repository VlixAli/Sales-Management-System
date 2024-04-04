package com.pluralsight.project.repositories;

import com.pluralsight.project.models.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionTypeRepository extends JpaRepository<ActionType,Long> {

}
