package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollResponseDto {
    private String lectureName;
    private int payment;
    private LocalDateTime enrolledAt;
}

