package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE acc_id = #{accId}")
    Optional<Admin> findByAccId(@Param("accId") Integer accId);
}
