package com.bunky.server.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	@RequestMapping("/myTest")
	public String helloTest() {
		return "My Test is working!";
	}

//	@Scheduled(cron = "0 0/2 * * * *")
	public void scheduleTaskUsingCronExpression() {
		System.out.println(
				"schedule tasks using cron jobs - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern( "MM/dd/yyyy HH:mm")));
	}

}