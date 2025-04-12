package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.AccountSimpleDto;
import com.wecompany.duoprojectv1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountSimpleDto>> getAllAccounts() {
        return ResponseEntity.ok(adminService.getAllAccounts());
    }
}
