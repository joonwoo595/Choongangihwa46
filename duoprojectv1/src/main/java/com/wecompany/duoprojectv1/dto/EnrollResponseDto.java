package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollResponseDto {
    private int enrollmentId;
    private String lectureName;
    private int payment;
    private LocalDateTime enrolledAt;
}

