package tdd.vendingmachine;

import java.util.List;

public class Bill
{
    private static final List<Integer> POSSIBLE_BILL_VALUES = List.of(1_000, 5_000, 10_000, 50_000);
    private final int value;

    public Bill(int value)
    {
        if (!POSSIBLE_BILL_VALUES.contains(value))
            throw new IllegalArgumentException();
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
