package com.wecompany.duoprojectv1.domain;

public enum UserType {
    ADMIN, STUDENT, INSTRUCTOR;

    public static UserType from(String value) {
        try {
            return UserType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 사용자 유형입니다.");
        }
    }
}