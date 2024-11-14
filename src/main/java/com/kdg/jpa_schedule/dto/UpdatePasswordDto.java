package com.kdg.jpa_schedule.dto;

import lombok.Getter;

@Getter
public class UpdatePasswordDto {

    private final String oldPassword;

    private final String newPassword;

    public UpdatePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
