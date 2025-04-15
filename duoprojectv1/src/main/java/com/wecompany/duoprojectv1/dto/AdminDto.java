package com.wecompany.duoprojectv1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto implements UserInfoDto {

    private String name;
    private String phone;

    @Override
    public String getType() {
        return "ADMIN";
    }

    @Override
    @JsonIgnore
    public void setType(String type) {
        // Do nothing
    }
}
