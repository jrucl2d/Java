package tdd.vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendingMachineMoneyTest
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
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertCoin(coin));
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
        int insertedCoin = vendingMachine.getInsertedCoinValue();

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
        int insertedCoin = vendingMachine.getInsertedCoinValue();

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
        int fifty = vendingMachine.getInsertedCoinValue();
        vendingMachine.insertCoin(100);
        int oneFifty = vendingMachine.getInsertedCoinValue();
        vendingMachine.insertCoin(500);
        int sixFifty = vendingMachine.getInsertedCoinValue();

        // then
        assertThat(fifty).isEqualTo(50);
        assertThat(oneFifty).isEqualTo(150);
        assertThat(sixFifty).isEqualTo(650);
    }

    @Test
    @DisplayName("자판기에 넣은 모든 동전 가격을 확인할 수 있다.")
    void name3()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(50);
        vendingMachine.insertCoin(50);
        vendingMachine.insertCoin(100);
        vendingMachine.insertCoin(500);

        // when
        int totalCoinValue = vendingMachine.getInsertedCoinValue();

        // then
        assertThat(totalCoinValue).isEqualTo(50 + 50 + 100 + 500);
    }

    @ParameterizedTest(name = "{0}원 지폐 넣기")
    @ValueSource(ints = {1000, 5000, 10000, 50000})
    @DisplayName("자판기에 지폐을 넣을 수 있다.")
    void name10(int bill)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        vendingMachine.insertBill(bill);
    }

    @ParameterizedTest(name = "{0}원 지폐 넣기")
    @ValueSource(ints = {1234124, -2392929, 2535235})
    @DisplayName("1000원, 5000원, 10000원, 50000원 이외의 지폐을 넣으면 IllegalArgumentException이 발생한다.")
    void name9(int bill)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertBill(bill));
    }

    @ParameterizedTest(name = "{0}원 지폐 넣기")
    @ValueSource(ints = {1000, 5000, 10000, 50000})
    @DisplayName("1000원, 5000원, 10000원, 50000원을 각각 넣으면 넣은 지폐 가격을 확인할 수 있다.")
    void name8(int bill)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertBill(bill);
        int insertedBill = vendingMachine.getInsertedBillValue();

        // then
        assertThat(insertedBill).isEqualTo(bill);
    }

    @Test
    @DisplayName("아무 지폐도 넣지 않은 자판기의 지폐 가격은 0원이다.")
    void name7()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        int insertedBill = vendingMachine.getInsertedBillValue();

        // then
        assertThat(insertedBill).isZero();
    }

    @Test
    @DisplayName("1000원, 5000원, 10000원, 50000원을 차례로 넣으면 누적된 지폐 가격을 확인할 수 있다.")
    void name5()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertBill(1000);
        int thousand = vendingMachine.getInsertedBillValue();
        vendingMachine.insertBill(5000);
        int fifty = vendingMachine.getInsertedBillValue();
        vendingMachine.insertBill(10000);
        int oneFifty = vendingMachine.getInsertedBillValue();
        vendingMachine.insertBill(50000);
        int sixFifty = vendingMachine.getInsertedBillValue();

        // then
        assertThat(thousand).isEqualTo(1000);
        assertThat(fifty).isEqualTo(1000 + 5000);
        assertThat(oneFifty).isEqualTo(1000 + 5000 + 10000);
        assertThat(sixFifty).isEqualTo(1000 + 5000 + 10000 + 50000);
    }

    @Test
    @DisplayName("자판기 내부에 존재하는 모든 지폐 가격을 확인할 수 있다.")
    void name6()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>());
        List<Integer> bills = List.of(1000, 5000, 10000, 50000, 5000);

        // when
        bills.forEach(vendingMachine::insertBill);
        int totalBillValue = vendingMachine.getInsertedBillValue();

        // then
        assertThat(totalBillValue).isEqualTo(1000 + 5000 + 10000 + 50000 + 5000);
    }

    @Test
    @DisplayName("한 번에 돈을 통째로 넣을 수 있다.")
    void name()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertMoney(100 + 500 + 50);
        int insertedCoinValue = vendingMachine.getInsertedCoinValue();

        // then
        assertThat(insertedCoinValue).isEqualTo(100 + 500 + 50);
    }

    @Test
    @DisplayName("동전과 지폐를 섞어서 한 번에 통째로 넣을 수 있다.")
    void name1234()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertMoney(10000 + 500 + 50 + 50000);
        int insertedCoinValue = vendingMachine.getInsertedCoinValue();
        int insertedBillValue = vendingMachine.getInsertedBillValue();

        // then
        assertThat(insertedCoinValue).isEqualTo(500 + 50);
        assertThat(insertedBillValue).isEqualTo(10000 + 50000);
    }

    @Test
    @DisplayName("자판기에 넣은 돈 총량을 확인할 수 있다.")
    void name1235()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        vendingMachine.insertMoney(10000 + 500 + 500 + 100 + 50 + 50000);
        int totalInsertedMoneyValue = vendingMachine.getTotalInsertedMoneyValue();

        // then
        assertThat(totalInsertedMoneyValue).isEqualTo(10000 + 500 + 100 + 500 + 50 + 50000);
    }

    @Test
    @DisplayName("자판기 내부에 존재하는 모든 돈 총량을 알 수 있다.")
    void name1111()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>(), 50000 + 100 + 500);

        // when
        vendingMachine.insertMoney(500 + 100 + 100);
        int totalMachineMoneyValue = vendingMachine.getTotalMachineMoneyValue();

        // then
        assertThat(totalMachineMoneyValue).isEqualTo(500 + 100 + 100 + 100 + 500 + 50000);
    }

    @Test
    @DisplayName("동전 혹은 지폐에 해당하지 않는 값을 넣으면 IllegalArgumentException 발생한다.")
    void name555()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertMoney(100 + 500 + 30));
    }
}
