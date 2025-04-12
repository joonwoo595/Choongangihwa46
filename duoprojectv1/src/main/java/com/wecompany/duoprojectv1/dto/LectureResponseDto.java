package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LectureResponseDto {
    private Integer cosId;
    private String cosName;
    private String instructorName; // 교수 이름 추가
    private String insName; // 추가
    private int price;
    private int totalMinutes; // 초 → 분 변환
    private LocalDate createDate;
    private int maxCapacity;
}

