package com.wecompany.duoprojectv1.repository;

import com.wecompany.duoprojectv1.domain.Instructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface InstructorRepository {
    @Select("SELECT * FROM instructor WHERE acc_id = #{accId}")
    Optional<Instructor> findByAccId(@Param("accId") Integer accId);
}
