package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE acc_id = #{accId}")
    Optional<Student> findByAccId(@Param("accId") Integer accId);

    @Select("SELECT * FROM student WHERE st_num = #{studentId}")
Optional<Student> findById(@Param("studentId") int studentId);

}
