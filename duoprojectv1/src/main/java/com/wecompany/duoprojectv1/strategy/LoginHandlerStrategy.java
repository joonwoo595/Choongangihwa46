package com.wecompany.duoprojectv1.strategy;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.dto.UserInfoDto;

public interface LoginHandlerStrategy {
    UserInfoDto buildUserInfo(Account account);
}