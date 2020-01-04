package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//Enables scanning for Servlet components (@WebFilter or @WebServlet or @WebListener). Scanning is only performed when using an embedded web server.
@ServletComponentScan
@EnableAsync // 开启异步执行的能力
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}

