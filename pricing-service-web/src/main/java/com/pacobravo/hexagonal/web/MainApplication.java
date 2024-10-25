package com.pacobravo.hexagonal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.pacobravo.hexagonal")
@EnableJpaRepositories(basePackages = "com.pacobravo.hexagonal.infrastructure.price.adapter.jpa")
@EntityScan(basePackages = "com.pacobravo.hexagonal.infrastructure.price.adapter.entity")
public class MainApplication {

    public static void main(String[] args) {

        try {
            SpringApplication application = new SpringApplication(MainApplication.class);
            application.run(args);
        } catch (Exception e){
            log.error("Error running pricing-service: ", e);
        }
    }
}