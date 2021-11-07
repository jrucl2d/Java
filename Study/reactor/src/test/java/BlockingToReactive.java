import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;

public class BlockingToReactive {
    @Test
    void name() throws InterruptedException {
        UserRepository userRepository = new UserRepository();
        Flux.defer(() -> Flux.fromIterable(userRepository.findAll()))
                .subscribeOn(Schedulers.boundedElastic()) // 다른 스레드에서 실행됨
                .subscribe(System.out::println);

        Thread.sleep(1000);
    }

    @Test
    void name1() {
        UserRepository userRepository = new UserRepository();

        Flux.range(1, 10)
                .map(i -> new User(i + ""))
                .publishOn(Schedulers.boundedElastic()) // publishOn은 이 이후의 chain에 영향을 줌
                .doOnNext(userRepository::save)
                .then();
    }

    private static class User {
        private final String name;

        private User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    private static class UserRepository {
        public List<User> findAll() {
            return Collections.singletonList(new User("haha"));
        }

        public void save(User user) {

        }
    }
}
