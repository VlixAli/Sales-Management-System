package com.pluralsight.project.repositories;

import com.pluralsight.project.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
