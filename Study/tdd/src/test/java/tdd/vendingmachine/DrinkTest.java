package tdd.vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DrinkTest
{
    @Test
    @DisplayName("음료수 이름, 가격을 가지는 음료수를 만들 수 있다.")
    void name() {
        // given
        Drink drink = new Drink("사이다", 1200);

        // when
        // then
        assertThat(drink).isNotNull();
    }

    @ParameterizedTest(name = "{0} : {1}원")
    @CsvSource({
        "사이다,1200",
        "콜라,1300",
        "식혜,1500"
    })
    @DisplayName("음료수의 가격과 이름을 알 수 있다.")
    void name1(String name, int price) {
        // given
        Drink drink = new Drink(name, price);

        // when
        String drinkName = drink.getName();
        int drinkPrice = drink.getPrice();

        // then
        assertThat(drinkName).isEqualTo(name);
        assertThat(drinkPrice).isEqualTo(price);
    }
}
