package tdd.vendingmachine;

public class Bill implements Money
{
    private final Price value;

    public Bill(Price value)
    {
        value.checkIsBill();
        this.value = value;
    }

    @Override
    public int getValue()
    {
        return value.getValue();
    }
}
