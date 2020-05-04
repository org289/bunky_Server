package com.bunky.server.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping("specialMsg")
	public String msg(){
		return "we are the BEST";
	}

	@RequestMapping("/mytest")
	public String helloTest() {
		return "My Test is working!";
	}



}