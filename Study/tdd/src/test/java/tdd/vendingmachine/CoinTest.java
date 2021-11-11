package tdd.vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinTest
{
    @ParameterizedTest(name = "{0}원 동전 생성하기")
    @ValueSource(ints = {50, 100, 500})
    @DisplayName("50원, 100원, 500짜리 동전을 생성할 수 있다.")
    void name(int value)
    {
        // given
        Coin coin = new Coin(value);

        // when
        // then
        assertThat(coin).isNotNull();
    }

    @ParameterizedTest(name = "{0}원 동전의 값 : {0}")
    @ValueSource(ints = {50, 100, 500})
    @DisplayName("생성한 동전의 값을 확인할 수 있다.")
    void name1(int value)
    {
        // given
        Coin coin = new Coin(value);

        // when
        int coinValue = coin.getValue();
        // then
        assertThat(coinValue).isEqualTo(value);
    }

    @ParameterizedTest(name = "{0}원 동전 생성 시도")
    @ValueSource(ints = {-100, 40, 123})
    @DisplayName("50원, 100원, 500원 이외의 동전을 생성하려 하면 IllegalArgumentException 발생한다.")
    void name2(int value)
    {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Coin(value));
    }
}
