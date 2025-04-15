package com.wecompany.duoprojectv1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorDto implements UserInfoDto {

    private String name;
    private int departmentId;
    private int majorId;

    @Override
    public String getType() {
        return "INSTRUCTOR";
    }

    @Override
    @JsonIgnore // 이걸 꼭 넣어야 중복 제거됨!
    public void setType(String type) {
        // Do nothing
    }
}
