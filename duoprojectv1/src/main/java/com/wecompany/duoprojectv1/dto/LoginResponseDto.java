package com.wecompany.duoprojectv1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String email;
    private String userType;
    private UserInfoDto userInfo;
}
