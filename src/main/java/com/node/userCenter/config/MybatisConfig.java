package com.node.userCenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.node.userCenter.mapper")
public class MybatisConfig {
}