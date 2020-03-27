package com.bunky.server.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping("/mytest")
	public String hello() {
		return "My Test is working!";
	}

//	TODO: search for dependency injection explaining
// TODO: look at this guide: https://spring.io/guides/gs/spring-boot/
}