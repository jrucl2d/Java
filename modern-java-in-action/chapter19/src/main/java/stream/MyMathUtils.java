package stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyMathUtils {
    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(MyMathUtils::isPrime)
                .limit(n);
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    public static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    public static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    public static IntStream primes(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(
                IntStream.of(head),
                primes(tail(numbers).filter(n -> n % head != 0))
        );
    }
}
