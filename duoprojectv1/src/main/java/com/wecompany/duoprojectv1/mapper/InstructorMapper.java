package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.EnrolledStudentDto;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InstructorMapper {

    Optional<Instructor> findByAccId(@Param("accId") Integer accId);

    // ❌ 어노테이션 제거
    Optional<Instructor> findById(@Param("insId") Integer insId);

   List<InstructorLectureDto> findLecturesByInstructor(@Param("insId") int insId);  // ✅ 일치시켜야 함


    List<EnrolledStudentDto> findEnrolledStudentsByInstructor(@Param("insId") int insId);

}
