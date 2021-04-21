package com.jikui.oasys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jikui.oasys.mapper")
@SpringBootApplication
public class OasysApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasysApplication.class, args);
    }

}
