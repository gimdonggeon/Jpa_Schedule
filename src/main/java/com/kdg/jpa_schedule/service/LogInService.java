package com.kdg.jpa_schedule.service;

import com.kdg.jpa_schedule.entity.UserLogIn;
import com.kdg.jpa_schedule.repository.UserLogInRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    @Autowired
    private UserLogInRepository userLogInRepository;  // Repository 인스턴스를 주입받음

    public void login(String email, String password, HttpSession session) {
        UserLogIn userLogIn = userLogInRepository.findByEmail(email)  // 인스턴스를 통해 호출
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if(!userLogIn.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        session.setAttribute("userLogIn", userLogIn);
    }

    public UserLogIn getUserLogInFromSession(HttpSession session) {
        return (UserLogIn) session.getAttribute("userLogIn");
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}

