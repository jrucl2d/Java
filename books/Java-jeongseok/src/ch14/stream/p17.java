package ch14.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConcatCollector implements Collector<String, StringBuilder, String> {

    @Override
    public Supplier<StringBuilder> supplier() {
        return () -> new StringBuilder();
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, s) -> sb.append(s);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (sb, sb2) -> sb.append(sb2);
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return sb -> sb.toString();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
public class p17 {
    public static void main(String[] args) {
        String[] arr = {"aaa", "bbb", "ccc"};
        Stream<String> stream = Stream.of(arr);
        
        String res = stream.collect(new ConcatCollector());
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("res = " + res);
    }
}
