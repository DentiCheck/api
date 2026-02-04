package com.denticheck.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DenticheckApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DenticheckApiApplication.class, args);
    }
}
