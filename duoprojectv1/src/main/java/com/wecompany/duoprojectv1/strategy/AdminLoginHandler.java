package com.wecompany.duoprojectv1.strategy;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.domain.Admin;
import com.wecompany.duoprojectv1.dto.AdminDto;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import com.wecompany.duoprojectv1.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component("ADMIN")
@RequiredArgsConstructor
public class AdminLoginHandler implements LoginHandlerStrategy {
    private final AdminMapper adminMapper;

    @Override
    public UserInfoDto buildUserInfo(Account account) {
        Admin admin = adminMapper.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "관리자 정보가 없습니다."));
        AdminDto dto = new AdminDto();
        dto.setName(admin.getAdminName());
        dto.setPhone(admin.getAdminPhone());
        dto.setType("ADMIN");
        return dto;
    }
}
