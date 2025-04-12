package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
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

    // service/InstructorService.java
public List<EnrolledStudentDto> getMyStudents(int accId) {
    return instructorMapper.findEnrolledStudentsByInstructor(accId);
}

}
