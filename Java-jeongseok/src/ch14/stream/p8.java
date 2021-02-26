package ch14.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class p8 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("aa", 3, 300),
                new Student("bb", 1, 200),
                new Student("cc", 2, 100),
                new Student("dd", 2, 150),
                new Student("ee", 1, 200),
                new Student("ff", 3, 290)
        );
        studentStream.sorted(Comparator.comparing(Student::getBan) // 반별 정렬
                            .thenComparing(Comparator.naturalOrder())) // 기본 정렬
                            .forEach(System.out::println);
    }
}
class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;
    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }
    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", ban=" + ban + ", totalScore=" + totalScore +'}';
    }
    public String getName() {
        return name;
    }
    public int getBan() {
        return ban;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public int compareTo(Student s){
        return s.totalScore - this.totalScore;
    }
}