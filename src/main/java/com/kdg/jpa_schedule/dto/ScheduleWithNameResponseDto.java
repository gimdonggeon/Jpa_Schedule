package com.kdg.jpa_schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleWithNameResponseDto {

    private final String title;

    private final String contents;

    private final String username;

    public ScheduleWithNameResponseDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
