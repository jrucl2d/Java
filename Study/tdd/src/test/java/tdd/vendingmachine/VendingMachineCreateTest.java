package tdd.vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendingMachineCreateTest
{
    @Test
    @DisplayName("음료수가 없는 자판기 객체를 생성할 수 있다.")
    void name()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @Test
    @DisplayName("빈 음료수 컬렉션을 가지고 자판기 객체를 생성할 수 있다.")
    void name234()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(Collections.emptyList());

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @Test
    @DisplayName("음료수가 들어있는 자판기 객체를 생성할 수 있다.")
    void name1()
    {
        // given
        List<String> drinks = Arrays.asList("사이다", "콜라", "식혜", "물");
        VendingMachine vendingMachine = new VendingMachine(drinks);

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @ParameterizedTest(name = "음료수 {1}개 : {0}")
    @MethodSource("drinkLists")
    @DisplayName("자판기에 들어있는 음료수를 확인할 수 있다.")
    void name2(List<String> drinks, int drinkNum)
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(drinks);

        // when
        List<String> allDrinks = vendingMachine.getAllDrinks();

        // then
        assertThat(allDrinks.size()).isEqualTo(drinkNum);
        assertThat(allDrinks).containsAll(drinks);
    }
    private static Stream<Arguments> drinkLists() {
        return Stream.of(
            Arguments.of(Arrays.asList("사이다", "콜라", "식혜", "물"), 4),
            Arguments.of(Arrays.asList("사이다", "콜라"), 2),
            Arguments.of(Collections.emptyList(), 0)
        );
    }

    @Test
    @DisplayName("빈 자판기의 음료수는 빈 리스트로 반환된다.")
    void name3()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine();

        // when
        List<String> allDrinks = vendingMachine.getAllDrinks();

        // then
        assertThat(allDrinks).isEmpty();
    }

    @Test
    @DisplayName("동전이 든 자판기를 생성할 수 있다.")
    void name4()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>(), 50 + 100 + 500);

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @Test
    @DisplayName("지폐가 든 자판기를 생성할 수 있다.")
    void name5()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>(), 5000 + 10000 + 50000);

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @Test
    @DisplayName("동전과 지폐가 함께 든 자판기를 생성할 수 있다.")
    void name6()
    {
        // given
        VendingMachine vendingMachine = new VendingMachine(new ArrayList<>(), 50000 + 100 + 500);

        // when
        // then
        assertThat(vendingMachine).isNotNull();
    }

    @Test
    @DisplayName("동전 혹은 지폐에 해당하지 않는 값이 든 자판기를 생성하면 IllegalArgumentException 발생한다.")
    void name7()
    {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new VendingMachine(new ArrayList<>(), 15 + 500));
    }
}
