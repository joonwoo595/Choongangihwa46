package com.wecompany.duoprojectv1.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Lecture {
    private Integer cosId;
    private String cosName;
    private int price;
    private int totalSecond;
    private LocalDate createDate;
    private int depId;
    private int maxCapacity;
    private Integer insId; // 교수 ID (외래키)
    // ✅ cosIns 제거 완료: 교수명은 instructor 테이블에서 조회
    private String insName; // 조회 전용, DB JOIN용
}
