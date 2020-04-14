package com.bunky.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BunkyServerApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(BunkyServerApplication.class, args);
    }

    // TODO: Class - feedback--> feedback(0 if valid 1 if there's err),error(string)
}
