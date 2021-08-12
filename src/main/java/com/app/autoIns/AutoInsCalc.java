package com.app.autoIns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "com.app.*", "com.app.service", "com.app.controller" })
@EnableSwagger2
public class AutoInsCalc {

	public static void main(String[] args) {
		SpringApplication.run(AutoInsCalc.class, args);
	}

}
