package com.ahfdkun.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahfdkun.api.service.HelloService;
import com.ahfdkun.api.dto.User2;

@RestController
public class RefactorHelloController implements HelloService {

	private final Logger logger = Logger.getLogger(RefactorHelloController.class);

	@Autowired
	private DiscoveryClient client; // 获取服务的信息
	
	public String hello(@RequestParam String name) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello4, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + name;
	}
	
	public User2 hello(@RequestHeader String name, @RequestHeader Integer age) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello5, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return new User2(name, age);
	}
	
	public String hello(@RequestBody User2 user2) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello6, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + user2.getName() + ", " + user2.getAge();
	}

}
