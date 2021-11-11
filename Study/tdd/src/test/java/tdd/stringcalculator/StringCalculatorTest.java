package tdd.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringCalculatorTest {
    @Test
    @DisplayName("null 또는 빈값")
    void name() {
        assertThat(StringCalculator.splitAndSum(null)).isZero();
        assertThat(StringCalculator.splitAndSum("")).isZero();
    }

    @Test
    @DisplayName("값 하나")
    void name1() {
        assertThat(StringCalculator.splitAndSum("12")).isEqualTo(12);
        assertThat(StringCalculator.splitAndSum("0")).isZero();
        assertThat(StringCalculator.splitAndSum("100")).isEqualTo(100);
    }

    @Test
    @DisplayName("쉼표 구분자")
    void name2() {
        assertThat(StringCalculator.splitAndSum("1234,4321")).isEqualTo(5555);
        assertThat(StringCalculator.splitAndSum("11,22,33")).isEqualTo(66);
    }

    @Test
    @DisplayName("쉼표 콜론 구분자")
    void name3() {
        assertThat(StringCalculator.splitAndSum("1,2;4")).isEqualTo(7);
        assertThat(StringCalculator.splitAndSum("44;3;2,1,3")).isEqualTo(44 + 3 + 2 + 1 + 3);
    }

    @Test
    @DisplayName("숫자 이외의 값 혹은 음수 넣으면 RuntimeException 발생")
    void name4() {
        Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.splitAndSum("-1,234,5"));
        Assertions.assertThrows(RuntimeException.class, () -> StringCalculator.splitAndSum("1,육칠,5"));
    }
}
