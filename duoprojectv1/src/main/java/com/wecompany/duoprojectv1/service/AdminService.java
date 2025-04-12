package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.dto.AccountSimpleDto;
import com.wecompany.duoprojectv1.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AccountMapper accountMapper;

    public List<AccountSimpleDto> getAllAccounts() {
        return accountMapper.findAllAccounts();
    }
}
