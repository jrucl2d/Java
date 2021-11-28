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
                    .andError(dr::setErrorResult)
                    .andAccept(s -> dr.setResult(s.getBody()));
            return "rest";
        }
    }

    @NoArgsConstructor
    public static class Completion<S, T> {
        protected Completion next;

        public static <S,T> Completion<S,T> from(ListenableFuture<T> lf) {
            Completion<S,T> c = new Completion<>();
            lf.addCallback(c::complete, c::error);
            return c;
        }

        public <V> Completion<T,V> andApply(Function<T, ListenableFuture<V>> fn) {
            Completion<T,V> c = new ApplyCompetion<>(fn);
            this.next = c;
            return c;
        }

        public void andAccept(Consumer<T> con) {
            this.next = new AcceptCompletion<>(con);
        }

        public Completion<T,T> andError(Consumer<Throwable> econ) {
            Completion<T,T> c = new ErrorCompletion<>(econ);
            this.next = c;
            return c;
        }

        protected void error(Throwable e) {
            if (next != null) {
                next.error(e); // error 다음에게 전파
            }
        }

        private void complete(T t) {
            if (next != null) {
                next.run(t);
            }
        }

        protected void run(S val) {
        }
    }

    public static class AcceptCompletion<S> extends Completion<S, Void> {
        private Consumer<S> con;
        public AcceptCompletion(Consumer<S> con) {
            this.con = con;
        }

        @Override
        protected void run(S val) {
            con.accept(val);
        }
    }

    public static class ErrorCompletion<T> extends Completion<T,T> {
        private Consumer<Throwable> econ;
        public ErrorCompletion(Consumer<Throwable> econ) {
            this.econ = econ;
        }

        @Override
        protected void run(T val) {
            if (super.next != null) {
                super.next.run(val);
            }
        }

        @Override
        protected void error(Throwable e) {
            econ.accept(e);
        }
    }

    public static class ApplyCompetion<S,T> extends Completion<S,T> {
        private Function<S, ListenableFuture<T>> fn;

        public ApplyCompetion(Function<S, ListenableFuture<T>> fn) {
            this.fn = fn;
        }

        @Override
        protected void run(S val) {
            ListenableFuture<T> lf = fn.apply(val);
            lf.addCallback(super::complete, super::error);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncRestTemplateApplication.class, args);
    }
}
