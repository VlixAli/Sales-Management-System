package com.pluralsight.project.repositories;

import com.pluralsight.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLastName(String name);

    Optional<User> findByEmail(String email);
}
