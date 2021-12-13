package com.example.generic.generic2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticGeneric {

    public static void main(String[] args) {
        Member<Long> longMember = Member.of(10L);
        log.info(String.valueOf(longMember));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Member<T> {
        private String name;
        private Integer age;
        private T info;

        @Builder
        private Member(String name, Integer age, T info) {
            this.name = name;
            this.age = age;
            this.info = info;
        }

        public static <T> Member<T> of(T info) {
            return Member.<T>builder() // 빌더와 static이 연계하는 경우
                .name("name")
                .age(1)
                .info(info)
                .build();
        }

        @Override
        public String toString()
        {
            return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", info=" + info +
                '}';
        }
    }
}
