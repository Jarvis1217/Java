package com.myself.mybatisDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myself.mybatisDemo.mapper")
public class MybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisDemoApplication.class, args);
	}

}
