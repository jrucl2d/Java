package ch14.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class p11 {
    public static void main(String[] args) {
        Stream<String[]> stArrStm = Stream.of(
                new String[] {"abc", "def", "jkl"},
                new String[] {"ABC", "DEF", "JKL"}
        );

        Stream<String> stream = stArrStm.flatMap(Arrays::stream);
        stream.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        String[] line = {
                "Believe or not It is true",
                "Do or do not There is no try"
        };
        Stream<String> lineStream = Arrays.stream(line);
        lineStream.flatMap(l -> Stream.of(l.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        Stream<String> s1 = Stream.of("AAA", "ABC", "bBb", "Dd");
        Stream<String> s2 = Stream.of("bbb", "aaa", "ccc", "dd");
        Stream<Stream<String>> streamStream = Stream.of(s1, s2);
        Stream<String> res = streamStream.map(s -> s.toArray(String[]::new))
                                            .flatMap(Arrays::stream);
        res.map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);
    }
}
