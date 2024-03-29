package com.example.reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
public class WebFluxApplication {
    private static final String URL1 = "http://localhost:8081/service?req={req}";
    private static final String URL2 = "http://localhost:8081/service2?req={req}";

    private final MyService myService;
    @Autowired
    public WebFluxApplication(MyService myService) {
        this.myService = myService;
    }

    private final WebClient webClient = WebClient.create();

    @GetMapping("/rest")
    public Mono<String> rest(int idx) {
        return webClient.get()
                .uri(URL1, idx)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
                .flatMap(res1 -> webClient.get().uri(URL2, res1).exchangeToMono(c -> c.bodyToMono(String.class)));
    }

    public static void main(String[] args) {
        System.setProperty("reactor.ipc.netty.workerCount", "2");
        System.setProperty("reactor.ipc.netty.pool.maxConnections", "2000");
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @Service
    public static class MyService {
        public String work(String req) {
            return req + "/asyncwork";
        }
    }
}
