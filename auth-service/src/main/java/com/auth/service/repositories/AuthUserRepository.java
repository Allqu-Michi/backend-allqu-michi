package com.auth.service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.service.models.User;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
