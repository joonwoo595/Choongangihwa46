package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    @Select("SELECT COUNT(*) FROM enrollment WHERE st_num = #{studentId} AND cos_id = #{lectureId}")
    int countDuplicateEnrollment(@Param("studentId") int studentId, @Param("lectureId") int lectureId);

    @Select("SELECT price FROM lecture WHERE cos_id = #{lectureId}")
    Integer getLecturePrice(@Param("lectureId") int lectureId); // ✅ 여기 중요!


    @Insert("INSERT INTO enrollment (st_num, cos_id, payment) VALUES (#{studentId}, #{lectureId}, #{payment})")
    void insertEnrollment(@Param("studentId") int studentId, @Param("lectureId") int lectureId, @Param("payment") int payment);

    @Select("""
    SELECT l.cos_name AS lectureName,
           e.payment AS payment,
           e.created_at AS enrolledAt
    FROM enrollment e
    JOIN lecture l ON e.cos_id = l.cos_id
    WHERE e.st_num = #{studentId}
""")
    List<EnrollResponseDto> findEnrollmentsByStudentId(@Param("studentId") int studentId);

    @Delete("DELETE FROM enrollment WHERE enr_id = #{enrollmentId}")
int deleteEnrollment(@Param("enrollmentId") int enrollmentId);


}

