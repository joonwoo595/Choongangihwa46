package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.domain.Lecture;
import com.wecompany.duoprojectv1.dto.LectureResponseDto;
import com.wecompany.duoprojectv1.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lectures")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/search")
public ResponseEntity<List<LectureResponseDto>> searchLectures(@RequestParam String keyword) {
    return ResponseEntity.ok(lectureService.searchLectures(keyword));
}

@GetMapping("/sort")
public ResponseEntity<List<LectureResponseDto>> sortLectures(@RequestParam String sortBy) {
    return ResponseEntity.ok(lectureService.sortLectures(sortBy));
}


}
