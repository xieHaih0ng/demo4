package com.newer;

import java.util.Date;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.newer.dao")
@SpringBootApplication
public class DemoApplication {
	
	 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
