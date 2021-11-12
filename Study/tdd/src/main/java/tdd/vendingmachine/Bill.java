package tdd.vendingmachine;

public class Bill implements Money
{
    private final Price value;

    public Bill(int value)
    {
        this.value = Price.ofBill(value);
    }

    @Override
    public int getValue()
    {
        return value.getValue();
    }
}
