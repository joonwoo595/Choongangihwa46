package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.EnrollRequestDto;
import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import com.wecompany.duoprojectv1.mapper.EnrollmentMapper;
import com.wecompany.duoprojectv1.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * 수강 신청 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;

    /**
     * 수강 신청 처리
     *
     * @param dto 수강 신청 요청 데이터 (studentId, lectureId)
     */
    public void registerEnrollment(EnrollRequestDto dto) {

        // 0. 학생 존재 여부 확인
        Student student = studentMapper.findById(dto.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 학생은 존재하지 않습니다."));

        // 1. 중복 수강 신청 여부 확인
        int duplicateCount = enrollmentMapper.countByStudentIdAndLectureId(dto.getStudentId(), dto.getLectureId());
        if (duplicateCount > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 수강 신청한 강의입니다.");
        }

        // 2. 강의 가격 조회
        Integer price = enrollmentMapper.getLecturePrice(dto.getLectureId());
        if (price == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 강의는 존재하지 않습니다.");
        }

        // 3. 수강 등록
        enrollmentMapper.insertEnrollment(dto.getStudentId(), dto.getLectureId(), price);
    }

    /**
     * 학생 ID로 수강 내역 조회
     *
     * @param studentId 학생 ID
     * @return 수강 내역 리스트
     */
    public List<EnrollResponseDto> getEnrollments(int studentId) {
        return enrollmentMapper.findEnrollmentsByStudentId(studentId);
    }

    /**
     * 수강 신청 취소
     *
     * @param enrollmentId 수강 ID
     */
    public void cancelEnrollment(int enrollmentId) {
        int deleted = enrollmentMapper.deleteEnrollment(enrollmentId);
        if (deleted == 0) {
            /**
 * 수강 신청과 관련된 예외 처리는 모두 ResponseStatusException을 사용하여 통일
 */
throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 학생은 존재하지 않습니다.");

        }
    }
}
