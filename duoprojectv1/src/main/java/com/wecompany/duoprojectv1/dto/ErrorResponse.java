package com.wecompany.duoprojectv1.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 공통적인 에러 응답 구조
 * - 모든 예외 응답 시 사용하는 DTO
 */
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;  // 에러 발생 시각
    private int status;               // HTTP 상태 코드
    private String message;           // 에러 메시지

    public ErrorResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
