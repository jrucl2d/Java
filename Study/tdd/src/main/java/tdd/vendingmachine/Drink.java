package tdd.vendingmachine;

public class Drink
{
    private final String name;
    private final int price;

    public Drink(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPrice()
    {
        return this.price;
    }
}
