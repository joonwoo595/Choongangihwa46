package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 수강 신청 관련 DB 연동을 담당하는 Mapper 인터페이스
 */
@Mapper
public interface EnrollmentMapper {

    /**
     * 학생과 강의에 대해 중복 수강 신청 여부를 체크
     *
     * @param studentId 학생 ID
     * @param lectureId 강의 ID
     * @return 중복된 수강 신청이 있으면 1, 없으면 0 반환
     */
    @Select("SELECT COUNT(*) FROM enrollment WHERE st_num = #{studentId} AND cos_id = #{lectureId}")
    int countByStudentIdAndLectureId(@Param("studentId") int studentId, @Param("lectureId") int lectureId);

    /**
     * 강의 ID로 강의 가격을 조회
     *
     * @param lectureId 강의 ID
     * @return 강의 가격 (없으면 null 반환)
     */
    @Select("SELECT price FROM lecture WHERE cos_id = #{lectureId}")
    Integer getLecturePrice(@Param("lectureId") int lectureId);

    /**
     * 수강 신청을 DB에 등록
     *
     * @param studentId 학생 ID
     * @param lectureId 강의 ID
     * @param payment   강의 가격
     */
    @Insert("""
    INSERT INTO enrollment (st_num, cos_id, payment, pay_id)
    VALUES (#{studentId}, #{lectureId}, #{payment}, #{payId})
""")
    int insertEnrollment(@Param("studentId") int studentId,
                         @Param("lectureId") int lectureId,
                         @Param("payment") int payment,
                         @Param("payId") int payId);


    /**
     * 학생 ID로 해당 학생의 수강 내역을 조회
     *
     * @param studentId 학생 ID
     * @return 학생의 수강 내역 리스트
     */
    @Select("""
        SELECT l.cos_name AS lectureName,
               e.payment AS payment,
               e.created_at AS enrolledAt
        FROM enrollment e
        JOIN lecture l ON e.cos_id = l.cos_id
        WHERE e.st_num = #{studentId}
    """)
    List<EnrollResponseDto> findEnrollmentsByStudentId(@Param("studentId") int studentId);

    /**
     * 수강 신청 취소
     *
     * @param enrollmentId 수강 ID
     * @return 삭제된 수강 신청의 개수
     */
    @Delete("DELETE FROM enrollment WHERE enr_id = #{enrollmentId}")
    int deleteEnrollment(@Param("enrollmentId") int enrollmentId);


    // 현재 수강 인원 조회
    @Select("SELECT COUNT(*) FROM enrollment WHERE cos_id = #{lectureId}")
    int countEnrollmentsByLectureId(@Param("lectureId") int lectureId);

    // 최대 수강 인원 조회}
    @Select("SELECT max_capacity FROM lecture WHERE cos_id = #{lectureId}")
    int getLectureMaxCapacity(@Param("lectureId") int lectureId);


    @Select("""
    SELECT max_capacity 
    FROM lecture 
    WHERE cos_id = #{lectureId}
    FOR UPDATE
""")
    int getLectureMaxCapacityWithLock(@Param("lectureId") int lectureId);

}