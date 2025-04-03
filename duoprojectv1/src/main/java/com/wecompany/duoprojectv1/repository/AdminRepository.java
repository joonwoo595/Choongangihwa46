package com.wecompany.duoprojectv1.repository;

import com.wecompany.duoprojectv1.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface AdminRepository {
    @Select("SELECT * FROM admin WHERE acc_id = #{accId}")
    Optional<Admin> findByAccId(@Param("accId") Integer accId);
}
