package com.bunky.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class BunkyServerApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(BunkyServerApplication.class, args);
    }

}
