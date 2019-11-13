package com.zui.test;


import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author zui
 */
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

//    @Bean
//    public Redisson initRedisson(){
//        Config config = new Config();
//        return new Redisson(config);
//    }

}
