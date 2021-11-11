package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine
{
    private final List<String> drinks;
    private final CoinBucket coinBucket = CoinBucket.emptyBucket();

    public VendingMachine() {
        this.drinks = new ArrayList<>();
    }
    public VendingMachine(List<String> drinks) {
        this.drinks = drinks;
    }

    public List<String> getAllDrinks()
    {
        return this.drinks;
    }

    public void insertCoin(int coin)
    {
        coinBucket.putCoin(coin);
    }

    public int getInsertedCoin()
    {
        return coinBucket.getTotalValue();
    }
}
