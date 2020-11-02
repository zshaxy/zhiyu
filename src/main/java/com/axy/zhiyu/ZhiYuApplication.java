package com.axy.zhiyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.axy.zhiyu.mapper")
@ComponentScan("com.axy")
public class ZhiYuApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhiYuApplication.class,args);
    }

}
