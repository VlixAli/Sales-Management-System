package com.pluralsight.project.repositories;

import com.pluralsight.project.models.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param,String> {
}
