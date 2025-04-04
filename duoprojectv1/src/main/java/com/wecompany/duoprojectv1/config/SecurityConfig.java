package com.wecompany.duoprojectv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {

    // BCryptPasswordEncoder 빈을 등록하여 비밀번호를 안전하게 암호화합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP 보안 설정: CSRF 보호 비활성화, 기본 로그인 폼 비활성화, 인증이 필요한 요청 설정
    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .authorizeRequests()
            .anyRequest().permitAll(); // ✅ 모든 요청 인증 없이 허용

    return http.build();
}


    // ResponseStatusException 예외를 처리하여 사용자에게 오류 메시지 제공
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", ex.getStatus().value());
        error.put("error", ex.getReason());
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
