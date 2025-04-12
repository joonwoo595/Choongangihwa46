package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountSimpleDto {
    private Integer accId;
    private String email;
    private String userType;
    private LocalDateTime createdAt;
}
