package tdd.vendingmachine;

public class Coin
{
    private final int value;

    public Coin(int value)
    {
        if (!(value == 50 || value == 100 || value == 500))
            throw new IllegalArgumentException();
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
