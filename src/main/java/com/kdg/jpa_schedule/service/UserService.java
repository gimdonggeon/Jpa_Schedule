package com.kdg.jpa_schedule.service;

import com.kdg.jpa_schedule.dto.SignUpResponseDto;
import com.kdg.jpa_schedule.dto.UserResponseDto;
import com.kdg.jpa_schedule.entity.User;
import com.kdg.jpa_schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String password, String email) {
        User user = new User(name, password, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다 = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail());
    }

    @Transactional
    public void updatepassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(oldPassword)) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        findUser.updatePassword(newPassword);
    }

    public void delete(Long id) {
    }
}
