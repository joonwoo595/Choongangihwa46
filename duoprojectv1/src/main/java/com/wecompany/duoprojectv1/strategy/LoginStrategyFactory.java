package com.wecompany.duoprojectv1.strategy;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginStrategyFactory {
    private final Map<String, LoginHandlerStrategy> strategyMap;

    public UserInfoDto getUserInfo(Account account) {
        LoginHandlerStrategy strategy = strategyMap.get(account.getUserType());
        if (strategy == null) throw new IllegalArgumentException("지원되지 않는 userType");
        return strategy.buildUserInfo(account);
    }
}
