package tdd.vendingmachine;

public class Coin implements Money
{
    private final Price value;

    public Coin(int value)
    {
        this.value = Price.ofCoin(value);
    }

    @Override
    public int getValue()
    {
        return value.getValue();
    }
}
