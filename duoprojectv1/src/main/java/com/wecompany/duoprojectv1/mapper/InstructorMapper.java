package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Instructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface InstructorMapper {
    @Select("SELECT * FROM instructor WHERE acc_id = #{accId}")
    Optional<Instructor> findByAccId(@Param("accId") Integer accId);
}
