package com.kdg.jpa_schedule.controller;

import com.kdg.jpa_schedule.dto.CreateScheduleRequestDto;
import com.kdg.jpa_schedule.dto.ScheduleResponseDto;
import com.kdg.jpa_schedule.dto.ScheduleWithNameResponseDto;
import com.kdg.jpa_schedule.entity.Schedule;
import com.kdg.jpa_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        String name = requestDto.getName();

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(title, contents, name);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto);
    }



    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return  new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithNameResponseDto> findById(@PathVariable Long id) {

        ScheduleWithNameResponseDto scheduleWithNameResponseDto = scheduleService.findById(id);

        return  new ResponseEntity<>(scheduleWithNameResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
