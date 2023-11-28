package com.safetynet.api;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.safetynet.*"})
public class ApiApplication implements CommandLineRunner {

    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ApiApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("LOG TEST - Alerts application is running...");
        logger.info("LOG TEST - Alerts application is running...");
        logger.error("LOG TEST - Alerts application is running...");
    }
}
