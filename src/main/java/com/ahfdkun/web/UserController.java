package com.ahfdkun.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private DiscoveryClient client; // 获取服务的信息

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable Long id) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/users/" + id + ", host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "getUser id: " + id;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String postUser(@RequestBody Long id) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/users/" + id + ", host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "postUser id: " + id;
	}

}
