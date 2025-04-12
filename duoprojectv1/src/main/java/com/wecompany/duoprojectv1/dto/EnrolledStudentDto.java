// dto/EnrolledStudentDto.java
package com.wecompany.duoprojectv1.dto;

import lombok.Data;

@Data
public class EnrolledStudentDto {
    private int stNum;
    private String stName;
    private String stDepartment;
    private int stYear;
    private String cosName;  // 수강 중인 강의명
}
