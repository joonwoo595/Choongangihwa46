package com.wecompany.duoprojectv1.exception;

import com.wecompany.duoprojectv1.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 애플리케이션 전역에서 발생하는 예외를 처리하는 클래스
 * - ResponseStatusException, MethodArgumentNotValidException, IllegalArgumentException 처리
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResponseStatusException을 처리하고, 에러 메시지 응답 반환
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getStatus().value(), ex.getReason());
        return new ResponseEntity<>(error, ex.getStatus());
    }

    /**
     * 잘못된 요청 파라미터로 발생한 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) // 설정한 메시지 추출
                .collect(Collectors.joining(", "));      // 여러 메시지면 ,로 연결

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), errorMessage);
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 비즈니스 로직 예외 처리 (주로 IllegalArgumentException 사용)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
