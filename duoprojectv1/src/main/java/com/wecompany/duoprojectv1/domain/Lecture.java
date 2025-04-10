package com.wecompany.duoprojectv1.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Lecture {
    private Integer cosId;
    private String cosName;
    private String cosIns;
    private int price;
    private int totalSecond;
    private LocalDate createDate;
    private int depId;
    private int maxCapacity;
}

