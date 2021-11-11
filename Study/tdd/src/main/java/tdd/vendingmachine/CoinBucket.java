package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class CoinBucket
{
    private final List<Coin> coins;

    public CoinBucket(List<Coin> coins)
    {
        this.coins = coins;
    }

    public static CoinBucket emptyBucket()
    {
        return new CoinBucket(new ArrayList<>());
    }

    public void putCoin(int coin)
    {
        this.coins.add(new Coin(coin));
    }

    public int getTotalValue()
    {
        return this.coins.stream().map(Coin::getValue).reduce(0, Integer::sum);
    }
}
