package example;

import io.reactivex.Observable;

import java.util.stream.IntStream;

public class ObservableFromArray {
    public static void main(String[] args) {
        // 배열 처리에는 fromArray 사용
        Integer[] arr = {100, 200, 300};
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);

        // 기본형의 경우 제대로 된 결과가 나오지 않음.
        int[] arr2 = {100, 200, 300};
        source = Observable.fromArray(toIntegerArray(arr2));
        source.subscribe(System.out::println);
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return IntStream.of(intArray).boxed().toArray(Integer[]::new);
    }
}
