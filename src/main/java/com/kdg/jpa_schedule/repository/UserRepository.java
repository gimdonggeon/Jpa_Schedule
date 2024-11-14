package com.kdg.jpa_schedule.repository;

import com.kdg.jpa_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    default User findUserByNameOrElseThrow(String name) {
        return findUserByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원명이 존재하지 않습니다 = " + name));
    }

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다 = " + id));
    }
}
