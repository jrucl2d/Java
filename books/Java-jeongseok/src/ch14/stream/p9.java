package ch14.stream;

import java.io.File;
import java.util.stream.Stream;

public class p9 {
    public static void main(String[] args) {
        File[] fileArr = {
                new File("Ex1.java"), new File("Ex1.bak"),
                new File("Ex2.java"), new File("Ex1"), new File("Ex1.txt")
        };
        Stream<File> fileStream = Stream.of(fileArr);

        Stream<String> filenameString = fileStream.map(File::getName);
        filenameString.forEach(System.out::println);

        fileStream = Stream.of(fileArr);

        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1) // 확장자 없는 것 제외
                .map(s -> s.substring(s.indexOf(".") + 1)) // 확장자 추출
                .map(String::toUpperCase) // 대문자로
                .distinct() // 중복 제거
                .forEach(System.out::println);
    }
}
