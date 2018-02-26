package com.mysecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



//告诉项目为springboot项目
@SpringBootApplication
//让类提供rest服务
@RestController
//文档生成器     在项目启动后 在网页中访问 localhost:8080/swagger-ui.html
//@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		//springboot启动
		System.out.println("项目启动");
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello security";
	}

}
