package com.wecompany.duoprojectv1.dto;

import lombok.Data;

@Data
public class InstructorDto implements UserInfoDto {
    private String type;
    private String name;
    private int departmentId;
    private int majorId;
}

