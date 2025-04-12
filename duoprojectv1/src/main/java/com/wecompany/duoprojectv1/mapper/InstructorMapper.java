package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.InstructorLectureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param; // ⭕


import java.util.List;
import java.util.Optional;

@Mapper
public interface InstructorMapper {
    @Select("SELECT * FROM instructor WHERE acc_id = #{accId}")
    Optional<Instructor> findByAccId(@Param("accId") Integer accId);

    @Select("""
    SELECT cos_id, cos_name, price, create_date
    FROM lecture
    WHERE cos_ins = (
        SELECT ins_name FROM instructor WHERE acc_id = #{accId}
    )
""")
    List<InstructorLectureDto> findLecturesByInstructor(@Param("accId") int accId);

}
