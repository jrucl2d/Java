package com.example.reactive.asyncresttemplate;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.function.Consumer;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class AsyncRestTemplateApplication {
    private static final String URL1 = "http://localhost:8081/service?req={req}";
    private static AsyncRestTemplate rt = new AsyncRestTemplate();

    @RestController
    public static class MyController {
        @GetMapping("/rest")
        public String rest(int idx) {
            DeferredResult<String> dr = new DeferredResult<>();
            Completion.from(rt.getForEntity(URL1, String.class, "h" + idx))
                    .andAccept(s -> dr.setResult(s.getBody()));
            return "rest";
        }
    }

    @NoArgsConstructor
    public static class Completion {
        private Completion next;
        private Consumer<ResponseEntity<String>> con;

        public Completion(Consumer<ResponseEntity<String>> con) {
            this.con = con;
        }

        public static Completion from(ListenableFuture<ResponseEntity<String>> lf) {
            Completion c = new Completion();
            lf.addCallback(c::complete, c::error);
            return c;
        }

        public void andAccept(Consumer<ResponseEntity<String>> con) {
            Completion c = new Completion(con);
            this.next = c;
        }

        private void error(Throwable e) {
        }

        private void complete(ResponseEntity<String> s) {
            if (next != null) {
                next.run(s);
            }
        }

        private void run(ResponseEntity<String> val) {
            if (con != null) {
                con.accept(val);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncRestTemplateApplication.class, args);
    }
}
