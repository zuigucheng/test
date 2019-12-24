package com.zui.test;


import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.LinkedList;

/**
 * @author zui
 */
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
