package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Lecture;
import com.wecompany.duoprojectv1.dto.LectureResponseDto;
import com.wecompany.duoprojectv1.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureMapper lectureMapper;

    public List<LectureResponseDto> getAllLectures() {
    return lectureMapper.findAll().stream()
        .map(this::toDto)
        .toList();
    }

    public LectureResponseDto getLectureById(int cosId) {
    Lecture lecture = lectureMapper.findById(cosId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강의 정보를 찾을 수 없습니다."));
    return toDto(lecture); // ✅ Lecture → DTO 변환
}


    private LectureResponseDto toDto(Lecture lecture) {
    LectureResponseDto dto = new LectureResponseDto();
    dto.setCosId(lecture.getCosId());
    dto.setCosName(lecture.getCosName());
    dto.setCosIns(lecture.getCosIns());
    dto.setPrice(lecture.getPrice());
    dto.setTotalMinutes(lecture.getTotalSecond() / 60);
    dto.setCreateDate(lecture.getCreateDate());
    dto.setMaxCapacity(lecture.getMaxCapacity());
    return dto;
}

}
