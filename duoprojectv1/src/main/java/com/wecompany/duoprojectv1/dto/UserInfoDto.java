package com.wecompany.duoprojectv1.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type" // DTO에 포함된 "type" 값을 기반으로 클래스 매핑
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = StudentDto.class, name = "STUDENT"),
    @JsonSubTypes.Type(value = AdminDto.class, name = "ADMIN"),
    @JsonSubTypes.Type(value = InstructorDto.class, name = "INSTRUCTOR")
})
public interface UserInfoDto {
    String getType();

    void setType(String type); // ✅ 추가
}

