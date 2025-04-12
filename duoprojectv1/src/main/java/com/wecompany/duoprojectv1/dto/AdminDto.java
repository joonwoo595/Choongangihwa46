package com.wecompany.duoprojectv1.dto;

import lombok.Data;

@Data
public class AdminDto implements UserInfoDto {
    private String type;
    private String name;
    private String phone;
}
