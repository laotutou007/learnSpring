package com.jimmy.holydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jimmy.holydemo.dao")
public class HolydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolydemoApplication.class, args);
    }

}
