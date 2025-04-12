package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.ErrorResponse;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import com.wecompany.duoprojectv1.exception.InstructorNotFoundException;
import com.wecompany.duoprojectv1.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    private static final int MIN_INS_ID = 1; // 최소 insId 값

   // 강의 목록 조회 메서드
@GetMapping("/{insId}/lectures")
public ResponseEntity<?> getMyLectures(@PathVariable Integer insId) {
    validateInsId(insId);
    return ResponseEntity.ok(instructorService.getMyLectures(insId));
}



@GetMapping("/{insId}/students")
public ResponseEntity<?> getMyStudents(@PathVariable Integer insId) {
    validateInsId(insId);
    return ResponseEntity.ok(instructorService.getMyStudents(insId));
}

@GetMapping("/{insId}")
public ResponseEntity<?> getInstructorById(@PathVariable Integer insId) {
    validateInsId(insId);
    return instructorService.findById(insId)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new InstructorNotFoundException("Instructor not found for id " + insId));
}

private void validateInsId(Integer insId) {
    if (insId == null || insId < 1) {
        throw new IllegalArgumentException("insId must be a positive number");
    }
}


}
