package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.LoginRequestDto;
import com.wecompany.duoprojectv1.dto.LoginResponseDto;
import com.wecompany.duoprojectv1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인 API, 로그인 요청을 받아 AuthService를 통해 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {

        // 로그인 요청을 처리하고 결과를 반환
        LoginResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
