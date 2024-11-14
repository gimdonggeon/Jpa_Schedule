package com.kdg.jpa_schedule.dto;

public class UserResponseDto {

    private final String name;

    private final String email;

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
