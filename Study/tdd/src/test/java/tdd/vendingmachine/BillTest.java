package tdd.vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BillTest
{
    @ParameterizedTest(name = "{0}원 지폐 생성하기")
    @ValueSource(ints = {1_000, 5_000, 10_000, 50_000})
    @DisplayName("1000, 5000, 10000, 50000원짜리 지폐를 생성할 수 있다.")
    void name(int value)
    {
        // given
        Bill bill = new Bill(value);

        // when
        // then
        assertThat(bill).isNotNull();
    }

    @ParameterizedTest(name = "{0}원 지폐의 값 : {0}")
    @ValueSource(ints = {1_000, 5_000, 10_000, 50_000})
    @DisplayName("생성한 지폐의 값을 확인할 수 있다.")
    void name1(int value)
    {
        // given
        Bill bill = new Bill(value);

        // when
        int billValue = bill.getValue();

        // then
        assertThat(billValue).isEqualTo(value);
    }

    @ParameterizedTest(name = "{0}원 지폐 생성 시도")
    @ValueSource(ints = {-1000, 400000, 123453})
    @DisplayName(" 1000원, 5000원, 10000원, 500000원 이외의 지폐를 생성하려면 IllegalArgmentException 발생한다.")
    void name2(int value)
    {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Bill(value));
    }
}
