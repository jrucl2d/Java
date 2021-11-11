package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoinBucket
{
    private final List<Coin> coins;

    private CoinBucket(List<Coin> coins)
    {
        this.coins = coins;
    }

    public static CoinBucket emptyBucket()
    {
        return new CoinBucket(new ArrayList<>());
    }

    public static CoinBucket of(List<Integer> coins)
    {
        return new CoinBucket(coins.stream().map(Coin::new).collect(Collectors.toList()));
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
