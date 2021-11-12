package tdd.vendingmachine;

import java.util.List;

public class Coin implements Money
{
    private static final List<Integer> POSSIBLE_COIN_VALUES = List.of(50, 100, 500);
    private final int value;

    public Coin(int value)
    {
        if (!POSSIBLE_COIN_VALUES.contains(value))
            throw new IllegalArgumentException();
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value;
    }
}
