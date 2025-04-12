package com.wecompany.duoprojectv1.service;

import java.util.Optional;
import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import com.wecompany.duoprojectv1.exception.InstructorNotFoundException;
import com.wecompany.duoprojectv1.mapper.InstructorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorMapper instructorMapper;

    public List<InstructorLectureDto> getMyLectures(int accId) {
        return instructorMapper.findLecturesByInstructor(accId);
    }

    public List<EnrolledStudentDto> getMyStudents(int accId) {
        return instructorMapper.findEnrolledStudentsByInstructor(accId);
    }

    // Optional<Instructor>를 반환하도록 수정
    public Optional<Instructor> findById(Integer insId) {
    return instructorMapper.findById(insId);
}

}
