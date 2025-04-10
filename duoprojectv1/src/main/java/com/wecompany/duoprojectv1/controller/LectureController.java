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

    @GetMapping
    public ResponseEntity<List<LectureResponseDto>> getAllLectures() {
        return ResponseEntity.ok(lectureService.getAllLectures());
    }

    @GetMapping("/{cosId}")
public ResponseEntity<LectureResponseDto> getLectureById(@PathVariable int cosId) {
    return ResponseEntity.ok(lectureService.getLectureById(cosId));
}

}
