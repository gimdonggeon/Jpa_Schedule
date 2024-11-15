package com.kdg.jpa_schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String title;
    private final String contents;
    private final SignUpRequestDto signUpRequestDto;

    public CreateScheduleRequestDto(String title, String contents, SignUpRequestDto signupRequestDto) {
        this.title = title;
        this.contents = contents;
        this.signUpRequestDto = signupRequestDto;
    }

    public String getName() {
        return signUpRequestDto.getName();
    }
}
