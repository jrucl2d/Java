package operators;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());
        // 1개의 값(dan)을 넘겨 받아서 여러 개의 값을 리턴(range)
        Function<Integer, Observable<String>> func = num ->
            Observable.range(1, 9)
                .map(row -> num + " * " + row + " = " + row * num);
        Observable.just(dan)
            .flatMap(func)
            .subscribe(System.out::println);
        System.out.println("--------------------");

        // 람다를 사용한 버전
        Observable.just(dan)
            .flatMap(num -> Observable.range(1, 9)
                .map(row -> num + " * " + row + " = " + row * num))
            .subscribe(System.out::println);
        System.out.println("--------------------");

        Observable.just(dan)
            .flatMap(gugu -> Observable.range(1, 9),
                (gugu, i) -> gugu + " * " + i + " = " + gugu * i)
            .subscribe(System.out::println);
        in.close();
    }
}
