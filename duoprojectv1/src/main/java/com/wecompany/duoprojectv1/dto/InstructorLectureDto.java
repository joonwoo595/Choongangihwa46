package com.wecompany.duoprojectv1.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InstructorLectureDto {
    private Integer cosId;
    private String cosName;
    private int price;
    private LocalDate createDate;
}
