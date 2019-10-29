package com.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.menu.dao")
@EnableAspectJAutoProxy
public class MenuSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuSystemApplication.class, args);
	}

}
