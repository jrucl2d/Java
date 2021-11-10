package tdd.datesub;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubDateTest {

    @Test
    void testGetYearDay() {
        assertThat(SubDate.getYearDay(1)).isZero(); // 1년 1월 1일이 최초라고 설정. 1년까지의 총 일수는 0일
        assertThat(SubDate.getYearDay(2)).isEqualTo(365); // 2년 1월 1일까지의 일수는 총 365일.
        assertThat(SubDate.getYearDay(5)).isEqualTo(365 + 365 + 365 + 366); // 윤년이 포함되어 있다.
    }

    @Test
    void testLeapYear() {
        assertThat(SubDate.isLeapYear(0)).isTrue();
        assertThat(SubDate.isLeapYear(1)).isFalse();
        assertThat(SubDate.isLeapYear(4)).isTrue();
        assertThat(SubDate.isLeapYear(1200)).isTrue();
        assertThat(SubDate.isLeapYear(700)).isFalse();
    }

    @Test
    void testGetMonthDay() {
        assertThat(SubDate.getMonthDay(1, true)).isZero();
        assertThat(SubDate.getMonthDay(2, false)).isEqualTo(31);
        assertThat(SubDate.getMonthDay(3, false)).isEqualTo(31 + 28);
        assertThat(SubDate.getMonthDay(3, true)).isEqualTo(31 + 29);
    }
}
