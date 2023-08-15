package com.douzone.wehago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WehagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WehagoApplication.class, args);

    }

}
