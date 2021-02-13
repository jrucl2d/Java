package com.yu.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DisplayNameGeneratorDemo {

    @Nested // 테스트 클래스 안에 중첩 구조로 테스트 클래스를 작성 가능
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_supported {
        @Test
        void if_it_is_zero(){
        }
        @DisplayName("음수는 년도에 사용할 수 없음")
        @ParameterizedTest(name = "예를 들어, 년도 {0}은 불가")
        @ValueSource(ints = {-1, -4})
        void if_it_is_negative(int year){
        }
    }

    @Nested // 아래는 테스트 이름과 포함하는 클래스를 연겨라여 완전한 문장을 생성함
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_a_leap_year {
        @Test
        void if_it_is_divisible_by_4_but_not_by_100(){
        }

        @ParameterizedTest(name = "년도 {0}는 윤년임")
        @ValueSource(ints = {2016, 2020, 2048})
        void if_it_is_one_of_the_following_years(int year){
        }
    }

}
