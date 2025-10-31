package com.rq.rubyyaicode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rq.rubyyaicode.mapper")
public class RubyyAiCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RubyyAiCodeApplication.class, args);
    }

}
