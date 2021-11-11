package tdd.vendingmachine;

import java.util.Collections;
import java.util.List;

public class VendingMachine
{
    private final List<String> drinks;
    private int coin;

    public VendingMachine() {
        this.drinks = Collections.emptyList();
        this.coin = 0;
    }
    public VendingMachine(List<String> drinks) {
        this.drinks = drinks;
        this.coin = 0;
    }

    public List<String> getAllDrinks()
    {
        return this.drinks;
    }

    public void insertCoin(int coin)
    {
        if (!(coin == 50 || coin == 100 || coin == 500))
            throw new IllegalArgumentException();
        this.coin += coin;
    }

    public int getInsertedCoin()
    {
        return coin;
    }
}
