package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import com.wecompany.duoprojectv1.mapper.EnrollmentMapper;
import com.wecompany.duoprojectv1.dto.EnrollRequestDto;
import com.wecompany.duoprojectv1.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;  // 이 부분 추가

    public void enroll(EnrollRequestDto dto) {
        // 0. 학생 존재 여부 확인
        Student student = studentMapper.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));

        // 1. 중복 체크
        int count = enrollmentMapper.countDuplicateEnrollment(dto.getStudentId(), dto.getLectureId());
        if (count > 0) {
            throw new IllegalArgumentException("이미 수강 신청한 강의입니다.");
        }

        // 2. 가격 조회
        Integer price = enrollmentMapper.getLecturePrice(dto.getLectureId());
        if (price == null) {
            throw new IllegalArgumentException("존재하지 않는 강의입니다.");
        }

        // 3. 등록
        enrollmentMapper.insertEnrollment(dto.getStudentId(), dto.getLectureId(), price);
    }

    public List<EnrollResponseDto> getEnrollments(int studentId) {
        return enrollmentMapper.findEnrollmentsByStudentId(studentId);
    }

}

