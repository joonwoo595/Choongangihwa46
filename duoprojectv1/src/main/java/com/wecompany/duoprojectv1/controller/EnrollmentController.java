package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.EnrollRequestDto;
import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import com.wecompany.duoprojectv1.dto.ErrorResponse;
import com.wecompany.duoprojectv1.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 수강 신청 관련 요청을 처리하는 컨트롤러
 * - 수강 신청 (POST)
 * - 수강 내역 조회 (GET)
 * - 수강 취소 (DELETE)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * 수강 신청 요청 처리
     *
     * @param enrollRequestDto 수강 신청 요청 데이터 (studentId, lectureId)
     * @return 수강 신청 성공 메시지 또는 예외 응답
     */
    @PostMapping("/enroll")
    public ResponseEntity<?> registerEnrollment(@Valid @RequestBody EnrollRequestDto enrollRequestDto) {
        try {
            enrollmentService.registerEnrollment(enrollRequestDto);
            return ResponseEntity.ok("수강 신청이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * 특정 학생의 수강 내역 조회
     *
     * @param studentId 학생 ID
     * @return 수강 내역 리스트
     */
    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollResponseDto>> getEnrollments(@RequestParam int studentId) {
        List<EnrollResponseDto> enrollments = enrollmentService.getEnrollments(studentId);
        return ResponseEntity.ok(enrollments);
    }

    /**
     * 수강 신청 취소
     *
     * @param enrollmentId 수강 ID
     * @return 수강 신청 취소 완료 메시지 또는 예외 응답
     */
    @DeleteMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<?> cancelEnrollment(@PathVariable int enrollmentId) {
        try {
            enrollmentService.cancelEnrollment(enrollmentId);
            return ResponseEntity.ok("수강 신청이 취소되었습니다.");
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
