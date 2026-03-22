package com.example.cafeteriamanagerment.repository;

import com.example.cafeteriamanagerment.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByPhone(String phone);

    Boolean existsByPhone(String phone ) ;
    Boolean existsByEmail(String email) ;




}
