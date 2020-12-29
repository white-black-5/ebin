package com.javaclimb.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 热更新、热加载
*1、ctrl+shift+a 搜索registry，勾选....app.running
*2、用快捷键ctrl+f9
* */
@SpringBootApplication
@MapperScan("com.javaclimb.test.dao")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
