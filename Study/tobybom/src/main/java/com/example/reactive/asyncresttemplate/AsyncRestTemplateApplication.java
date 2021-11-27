package com.example.reactive.asyncresttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AsyncRestTemplateApplication {

    @RestController
    public static class MyController {
        public String rest() {
            return "rest";
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(AsyncRestTemplateApplication.class, args);
    }
}
