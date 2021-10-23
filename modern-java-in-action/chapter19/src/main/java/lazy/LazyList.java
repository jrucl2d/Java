package lazy;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    private final T head;
    private final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }

    public static <T> void printAll(MyList<T> list) {
        if (list.isEmpty())
            return;
        System.out.println(list.head());
        printAll(list.tail());
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get(); // supplier 의 lazy 동작을 만들었음
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
