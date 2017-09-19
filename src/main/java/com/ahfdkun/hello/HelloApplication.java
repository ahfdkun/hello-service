package com.ahfdkun.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ahfdkun.web.HelloController;

// 激活Eureka中的DisConveryClient实现
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackageClasses = HelloController.class)
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
