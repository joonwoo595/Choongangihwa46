package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param; // ⭕


import java.util.List;
import java.util.Optional;

@Mapper
public interface InstructorMapper {
    Optional<Instructor> findByAccId(@Param("accId") Integer accId);

    List<InstructorLectureDto> findLecturesByInstructor(@Param("accId") int accId);

    List<EnrolledStudentDto> findEnrolledStudentsByInstructor(@Param("accId") int accId);
}

