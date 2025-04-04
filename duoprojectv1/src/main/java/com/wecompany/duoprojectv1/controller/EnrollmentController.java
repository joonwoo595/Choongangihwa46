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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@Valid @RequestBody EnrollRequestDto dto) {
        try {
            enrollmentService.enroll(dto);
            return ResponseEntity.ok("수강 신청 성공");
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }


    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollResponseDto>> getEnrollments(@RequestParam int studentId) {
        List<EnrollResponseDto> enrollments = enrollmentService.getEnrollments(studentId);
        return ResponseEntity.ok(enrollments);
    }


}


