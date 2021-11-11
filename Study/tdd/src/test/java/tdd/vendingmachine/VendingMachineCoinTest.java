package tdd.vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineCoinTest
{
    @ParameterizedTest(name = "{0}원 동전 넣기")
    @ValueSource(ints = {50, 100, 500})
    @DisplayName("자판기에 동전을 넣을 수 있다.")
    void name(int coin)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        vendingMachine.insertCoin(coin);
    }

    @ParameterizedTest(name = "{0}원 동전 넣기")
    @ValueSource(ints = {40, -50, 20})
    @DisplayName("50원, 100원, 500원 이외의 동전을 넣으면 IllegalArgumentException이 발생한다.")
    void name1(int coin)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertCoin(coin));
    }

    @ParameterizedTest(name = "{0}원 동전 넣기")
    @ValueSource(ints = {50, 100, 500})
    @DisplayName("50원, 100원, 500원을 각각 넣으면 넣은 동전 가격을 확인할 수 있다.")
    void name3(int coin)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertCoin(coin);
        int insertedCoin = vendingMachine.getInsertedCoin();

        // then
        assertThat(insertedCoin).isEqualTo(coin);
    }

    @Test
    @DisplayName("아무 동전도 넣지 않은 자판기의 동전 가격은 0원이다.")
    void name4()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        int insertedCoin = vendingMachine.getInsertedCoin();

        // then
        assertThat(insertedCoin).isZero();
    }

    @Test
    @DisplayName("50원, 100원, 500원을 차례로 넣으면 누적된 동전 가격을 확인할 수 있다.")
    void name2()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertCoin(50);
        int fifty = vendingMachine.getInsertedCoin();
        vendingMachine.insertCoin(100);
        int oneFifty = vendingMachine.getInsertedCoin();
        vendingMachine.insertCoin(500);
        int sixFifty = vendingMachine.getInsertedCoin();

        // then
        assertThat(fifty).isEqualTo(50);
        assertThat(oneFifty).isEqualTo(150);
        assertThat(sixFifty).isEqualTo(650);
    }

    @Test
    @DisplayName("자판기 내부에 존재하는 모든 동전 가격을 확인할 수 있다.")
    void name3()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>(), List.of(50, 50, 100, 500));

        // when
        int totalCoinValue = vendingMachine.getTotalCoinValue();

        // then
        assertThat(totalCoinValue).isEqualTo(50 + 50 + 100 + 500);
    }
}
