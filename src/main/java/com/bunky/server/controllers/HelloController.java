package com.bunky.server.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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

}