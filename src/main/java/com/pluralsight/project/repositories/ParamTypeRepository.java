package com.pluralsight.project.repositories;

import com.pluralsight.project.models.ParamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamTypeRepository extends JpaRepository<ParamType,Long> {
}
