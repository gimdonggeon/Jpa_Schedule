package com.kdg.jpa_schedule.controller;

import com.kdg.jpa_schedule.entity.UserLogIn;
import com.kdg.jpa_schedule.service.LogInService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    @Autowired
    private LogInService logInService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            logInService.login(email, password, session);
            return "로그인 성공";
        } catch (RuntimeException e) {
            return "로그인 실패: " + e.getMessage();
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        logInService.logout(session);
        return "로그아웃 성공";
    }

    @GetMapping("/user")
    public String getUserInfo(HttpSession session) {
        UserLogIn userLogIn = logInService.getUserLogInFromSession(session);
        if (userLogIn != null) {
            return "인증된 사용자: " + userLogIn.getEmail();
        } else {
            return "로그인 필요";
        }
    }
}