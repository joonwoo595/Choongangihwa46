package com.wecompany.duoprojectv1.repository;

import com.wecompany.duoprojectv1.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Mapper
public interface AccountRepository {
    @Select("""
    SELECT
        acc_id AS accId,
        email,
        passwd,
        user_type AS userType,
        created_at AS createdAt
    FROM account
    WHERE email = #{email}
""")
Optional<Account> findByEmail(@Param("email") String email);

}
