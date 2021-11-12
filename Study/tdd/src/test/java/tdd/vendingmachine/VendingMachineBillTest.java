package tdd.vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineBillTest
{
    @ParameterizedTest(name = "{0}원 지폐 넣기")
    @ValueSource(ints = {1000, 5000, 10000, 50000})
    @DisplayName("자판기에 지폐을 넣을 수 있다.")
    void name(int bill)
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
    void name1(int bill)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertBill(bill));
    }

    @ParameterizedTest(name = "{0}원 지폐 넣기")
    @ValueSource(ints = {1000, 5000, 10000, 50000})
    @DisplayName("1000원, 5000원, 10000원, 50000원을 각각 넣으면 넣은 지폐 가격을 확인할 수 있다.")
    void name3(int bill)
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
    void name4()
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
    void name2()
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
    void name3()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>());
        List<Integer> bills = List.of(1000, 5000, 10000, 50000, 5000);

        // when
        bills.forEach(vendingMachine::insertBill);
        int totalBillValue = vendingMachine.getTotalBillValue();

        // then
        assertThat(totalBillValue).isEqualTo(1000 + 5000 + 10000 + 50000 + 5000);
    }
}
