package com.jaemin.spring02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 해당 패키지에서 Mapper로 인식해라
@MapperScan(basePackages = { "com.jaemin.spring02.mapper" })
public class MyBatisConfig {
    
}
