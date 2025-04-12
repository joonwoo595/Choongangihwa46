package com.wecompany.duoprojectv1.dto;

import lombok.Data;

@Data
public class StudentDto implements UserInfoDto {
    private String type;
    private String name;
    private String department;
    private int year;
}

