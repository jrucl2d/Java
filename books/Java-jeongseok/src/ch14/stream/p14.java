package ch14.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class p14 {
    public static void main(String[] args) {
        Student[] star = {
                new Student("aa", 3, 300),
                new Student("bb", 1, 200),
                new Student("cc", 2, 100),
                new Student("dd", 2, 150),
                new Student("ee", 1, 200),
                new Student("ff", 3, 290)
        };
        List<String> names = Stream.of(star).map(Student::getName)
                                            .collect(Collectors.toList());
        System.out.println("names = " + names);
        
        Student[] stuarr2 = Stream.of(star).toArray(Student[]::new);
        for (Student student : stuarr2) {
            System.out.println("student = " + student);
        }

        Map<String, Student> map = Stream.of(star).collect(Collectors.toMap(s -> s.getName(), p -> p));
        for (String s : map.keySet()) {
            System.out.println("map.get(s) = " + map.get(s));
        }
    }
}
