package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.AccountSimpleDto;
import com.wecompany.duoprojectv1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/accounts") // 모든 사용자용 (예: 테스트, 내부 용도)
    public ResponseEntity<List<AccountSimpleDto>> getAllAccounts() {
        return ResponseEntity.ok(adminService.getAllAccounts());
    }

    @GetMapping("/all-accounts") // 관리자만 접근 가능한 엔드포인트
    public ResponseEntity<?> getAllAccountsWithAuth(@RequestHeader("X-USER-TYPE") String userType) {
        if (!"ADMIN".equalsIgnoreCase(userType)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("관리자만 접근할 수 있는 기능입니다.");
        }
        return ResponseEntity.ok(adminService.getAllAccounts());
    }




}
