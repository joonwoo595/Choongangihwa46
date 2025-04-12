package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import com.wecompany.duoprojectv1.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/lectures")
    public ResponseEntity<List<InstructorLectureDto>> getMyLectures(@RequestParam int accId) {
        return ResponseEntity.ok(instructorService.getMyLectures(accId));
    }

    // controller/InstructorController.java
@GetMapping("/students")
public ResponseEntity<List<EnrolledStudentDto>> getMyStudents(@RequestParam int accId) {
    return ResponseEntity.ok(instructorService.getMyStudents(accId));
}

}
