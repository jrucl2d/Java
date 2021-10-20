package asyncthread;

import java.util.ArrayList;
import java.util.List;

public class FlowExample {

    public static void main(String[] args) {
        SimpleCell c1 = new SimpleCell("c1");
        SimpleCell c2 = new SimpleCell("c2");
        SimpleCell c3 = new SimpleCell("c3");

        c1.subscribe(c3);

        c1.onNext(10);
        c2.onNext(20);

        ArithmeticCell c4 = new ArithmeticCell("c4");
        SimpleCell c5 = new SimpleCell("c5");
        SimpleCell c6 = new SimpleCell("c6");

        c5.subscribe(c4::setLeft);
        c6.subscribe(c4::setRight);

        c4.onNext(10);
        c5.onNext(20);
        c4.onNext(15);
    }

    private static class ArithmeticCell extends SimpleCell {
        private int left;
        private int right;

        public ArithmeticCell(String name) {
            super(name);
        }

        public void setLeft(int left) {
            this.left = left;
            onNext(left + this.right);
        }

        public void setRight(int right) {
            this.right = right;
            onNext(right + this.left);
        }
    }

    private static class SimpleCell implements Subscriber<Integer>, Publisher<Integer> {
        private int value = 0;
        private String name;
        List<Subscriber> subscribers = new ArrayList<>();
        public SimpleCell(String name) {
            this.name = name;
        }

        @Override
        public void subscribe(Subscriber<? super Integer> subscriber) {
            subscribers.add(subscriber);
        }

        // 새로운 값을 구독자에게 알리는 메소드
        private void notifyAllSubscribers() {
            subscribers.forEach(subscriber -> subscriber.onNext(this.value));
        }

        @Override
        public void onNext(Integer newValue) {
            this.value = newValue;
            System.out.println(this.name + ":" + this.value);
            notifyAllSubscribers(); // 값이 갱신되었음을 모두에게 알림
        }
    }

    private interface Publisher<T> {
        void subscribe(Subscriber<? super T> subscriber);
    }

    private interface Subscriber<T> {
        void onNext(T t);
    }
}
