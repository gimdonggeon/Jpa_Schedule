package com.kdg.jpa_schedule.repository;

import com.kdg.jpa_schedule.entity.UserLogIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLogInRepository extends JpaRepository<UserLogIn, Long> {
    Optional<UserLogIn> findByEmail(String email);
}
