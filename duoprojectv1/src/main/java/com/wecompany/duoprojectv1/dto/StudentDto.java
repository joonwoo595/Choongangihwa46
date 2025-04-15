package com.wecompany.duoprojectv1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import lombok.Data;

@Data
public class StudentDto implements UserInfoDto {
    private String name;
    private String department;
    private int year;

    @Override
    @JsonIgnore
    public String getType() {
        return "STUDENT";
    }

    @Override
    public void setType(String type) {
        // 아무것도 안 해도 됨. 또는 예외 던져도 무방
    }
}

