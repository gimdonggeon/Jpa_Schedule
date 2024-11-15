package com.kdg.jpa_schedule.service;

import com.kdg.jpa_schedule.dto.ScheduleResponseDto;
import com.kdg.jpa_schedule.dto.ScheduleWithNameResponseDto;
import com.kdg.jpa_schedule.entity.Schedule;
import com.kdg.jpa_schedule.entity.User;
import com.kdg.jpa_schedule.repository.ScheduleRepository;
import com.kdg.jpa_schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String name) {
        User findUser = userRepository.findUserByNameOrElseThrow(name);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleWithNameResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User username = findSchedule.getUser();

        return new ScheduleWithNameResponseDto(findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getName());
    }
    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
