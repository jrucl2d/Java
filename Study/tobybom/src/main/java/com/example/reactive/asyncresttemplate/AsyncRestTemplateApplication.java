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
import java.util.function.Function;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class AsyncRestTemplateApplication {
    private static final String URL1 = "http://localhost:8081/service?req={req}";
    private static final String URL2 = "http://localhost:8081/service2?req={req}";
    private static AsyncRestTemplate rt = new AsyncRestTemplate();

    @RestController
    public static class MyController {
        @GetMapping("/rest")
        public String rest(int idx) {
            DeferredResult<String> dr = new DeferredResult<>();
            Completion.from(rt.getForEntity(URL1, String.class, "h" + idx))
                    .andApply(s -> rt.getForEntity(URL2, String.class, s.getBody()))
                    .andAccept(s -> dr.setResult(s.getBody()));
            return "rest";
        }
    }

    @NoArgsConstructor
    public static class Completion {
        private Completion next;

        public static Completion from(ListenableFuture<ResponseEntity<String>> lf) {
            Completion c = new Completion();
            lf.addCallback(c::complete, c::error);
            return c;
        }

        public Completion andApply(Function<ResponseEntity<String>, ListenableFuture<ResponseEntity<String>>> fn) {
            Completion c = new ApplyCompetion(fn);
            this.next = c;
            return c;
        }

        public void andAccept(Consumer<ResponseEntity<String>> con) {
            this.next = new AcceptCompletion(con);
        }

        private void error(Throwable e) {
        }

        private void complete(ResponseEntity<String> s) {
            if (next != null) {
                next.run(s);
            }
        }

        protected void run(ResponseEntity<String> val) {
        }
    }

    public static class AcceptCompletion extends Completion {
        private Consumer<ResponseEntity<String>> con;
        public AcceptCompletion(Consumer<ResponseEntity<String>> con) {
            this.con = con;
        }

        @Override
        protected void run(ResponseEntity<String> val) {
            con.accept(val);
        }
    }

    public static class ApplyCompetion extends Completion {
        private Function<ResponseEntity<String>, ListenableFuture<ResponseEntity<String>>> fn;

        public ApplyCompetion(Function<ResponseEntity<String>, ListenableFuture<ResponseEntity<String>>> fn) {
            this.fn = fn;
        }

        @Override
        protected void run(ResponseEntity<String> val) {
            ListenableFuture<ResponseEntity<String>> lf = fn.apply(val);
            lf.addCallback(super::complete, super::error);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncRestTemplateApplication.class, args);
    }
}
