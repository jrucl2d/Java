package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MoneyBucket
{
    private final List<Money> coins;

    private MoneyBucket(List<Money> coins)
    {
        this.coins = coins;
    }

    public static MoneyBucket emptyBucket()
    {
        return new MoneyBucket(new ArrayList<>());
    }

    public static MoneyBucket of(List<Integer> coins)
    {
        return new MoneyBucket(coins.stream().map(Coin::new).collect(Collectors.toList()));
    }

    public void putCoin(int coin)
    {
        this.coins.add(new Coin(coin));
    }

    public int getTotalValue()
    {
        return this.coins.stream().map(Money::getValue).reduce(0, Integer::sum);
    }
}
