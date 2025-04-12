package com.wecompany.duoprojectv1.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wecompany.duoprojectv1.mapper") // 패키지 경로 맞추기
public class MyBatisConfig {
    // MyBatis 매퍼 스캔 설정
}
