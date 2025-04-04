package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EnrollRequestDto {

    @NotNull(message = "studentId는 필수입니다.")
    @Min(value = 1, message = "studentId는 1 이상이어야 합니다.")
    private Integer studentId;

    @NotNull(message = "lectureId는 필수입니다.")
    @Min(value = 1, message = "lectureId는 1 이상이어야 합니다.")
    private Integer lectureId;
}
