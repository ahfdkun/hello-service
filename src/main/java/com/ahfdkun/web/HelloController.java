package com.ahfdkun.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahfdkun.domain.User2;

@RestController
public class HelloController {

	private final Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client; // 获取服务的信息

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(HttpServletRequest request) throws Exception {
		System.out.println("-------------Print Sleuth Headers-------------");
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String key = headers.nextElement();
			System.out.println(key + "--------->" + request.getHeader(key));
		}
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello World";
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam String name) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello1, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User2 hello(@RequestHeader String name, @RequestHeader Integer age) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello2, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return new User2(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody User2 user2) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello3, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + user2.getName() + ", " + user2.getAge();
	}

}
