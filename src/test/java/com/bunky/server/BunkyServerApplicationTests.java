package com.bunky.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class BunkyServerApplicationTests {

    @Test
    void contextLoads() {
        LocalDate localDate = LocalDate.of(2020, 5, 25);
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.println(localDate.format(format));
        System.out.println(today.format(format));
        int x = 5;
    }
}
