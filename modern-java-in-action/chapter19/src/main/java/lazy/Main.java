package lazy;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new MyLinkedList.Empty<>()));

        LazyList<Integer> numbers = from(2);
//        int two = numbers.head();
//        int three = numbers.tail().head();
//        System.out.println(two + " " + three);
//        int four = numbers.tail().tail().head();
//        System.out.println(four);

        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();
        System.out.println(two + " " + three + " " + five);
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0)
                )
        );
    }
}
