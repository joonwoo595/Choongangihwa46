package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {
    private String lectureName;
    private int amount;
    private LocalDateTime paidAt; // ← 변경!
}
