package tdd.vendingmachine;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return price == drink.price && Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, price);
    }
}
