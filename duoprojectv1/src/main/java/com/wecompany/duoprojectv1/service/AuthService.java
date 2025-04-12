package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.dto.LoginRequestDto;
import com.wecompany.duoprojectv1.dto.LoginResponseDto;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import com.wecompany.duoprojectv1.mapper.AccountMapper;
import com.wecompany.duoprojectv1.strategy.LoginStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountMapper accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginStrategyFactory loginStrategyFactory;

    // 로그인 처리 메소드
    public LoginResponseDto login(LoginRequestDto dto) {
        // 이메일로 계정 조회
        Account account = getAccountByEmail(dto.getEmail());

        // 비밀번호 검증
        verifyPassword(dto.getPasswd(), account.getPasswd());

        // 로그인 응답 데이터 빌드
        return LoginResponseDto.builder()
                .email(account.getEmail())
                .userType(account.getUserType())
                .userInfo(loginStrategyFactory.getUserInfo(account))
                .build();
    }

    // 이메일로 계정 조회
    private Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 존재하지 않습니다."));
    }

    // 비밀번호 검증
    private void verifyPassword(String inputPassword, String storedPassword) {
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }
}
