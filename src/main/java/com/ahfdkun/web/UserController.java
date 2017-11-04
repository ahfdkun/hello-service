package com.ahfdkun.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahfdkun.domain.User;

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

	@RequestMapping(value = "/users1", method = RequestMethod.GET)
	public List<User> getUser1(String ids) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/users1?ids" + ids + ", host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		String[] idsAttr = StringUtils.split(ids, ",");
		List<User> users = new ArrayList<>();
		for (String id : idsAttr) {
			users.add(new User(Long.valueOf(id), "name" + id));
		}
		return users;
	}

}
