package com.wecompany.duoprojectv1.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private Integer accId;
    private String email;
    private String passwd;
    private String userType;
    private LocalDateTime createdAt;
}

