package com.wecompany.duoprojectv1.dto;

import com.wecompany.duoprojectv1.dto.UserInfoDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDto {
    private String email;
    private String userType;
    private UserInfoDto userInfo;
}
