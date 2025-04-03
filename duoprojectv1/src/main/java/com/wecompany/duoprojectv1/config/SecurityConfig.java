package com.wecompany.duoprojectv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // CSRF 비활성화 (개발용)
            .formLogin().disable() // 기본 로그인 폼 비활성화
            .httpBasic().disable() // Basic 인증도 비활성화
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // 로그인 관련은 인증 없이 접근 가능
                .anyRequest().authenticated(); // 그 외에는 인증 필요 (선택사항)

        return http.build();
    }
}
