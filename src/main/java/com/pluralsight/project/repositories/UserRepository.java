package com.pluralsight.project.repositories;

import com.pluralsight.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLastName(String name);
}
