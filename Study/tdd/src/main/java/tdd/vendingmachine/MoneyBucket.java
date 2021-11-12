package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

public class MoneyBucket
{
    private final List<Money> monies;

    private MoneyBucket(List<Money> monies)
    {
        this.monies = monies;
    }

    public static MoneyBucket emptyBucket()
    {
        return new MoneyBucket(new ArrayList<>());
    }

    public static MoneyBucket of(int totalValue)
    {
        MoneyBucket moneyBucket = emptyBucket();
        totalValue = moneyBucket.calculateMoney(totalValue, 0, 4, moneyBucket::putBill);
        totalValue = moneyBucket.calculateMoney(totalValue, 4, 7, moneyBucket::putCoin);
        if (totalValue != 0)
            throw new IllegalArgumentException();
        return moneyBucket;
    }

    private int calculateMoney(int totalValue, int from, int to, IntConsumer putFunction)
    {
        int[] possibles = {50000, 10000, 5000, 1000, 500, 100, 50};
        for (int i = from; i < to; i++) {
            int money = possibles[i];
            int count = totalValue / money;
            if (count == 0) continue;
            totalValue -= money * count;
            for (int j = 0; j < count; j++) {
                putFunction.accept(money);
            }
        }
        return totalValue;
    }

    public void putMoney(int money)
    {
        money = this.calculateMoney(money, 0, 4, this::putBill);
        money = this.calculateMoney(money, 4, 7, this::putCoin);
        if (money != 0)
            throw new IllegalArgumentException();
    }

    public void putCoin(int coin)
    {
        this.monies.add(new Coin(coin));
    }

    public void putBill(int bill)
    {
        this.monies.add(new Bill(Price.of(bill)));
    }

    public int getTotalValue()
    {
        return this.monies.stream().map(Money::getValue).reduce(0, Integer::sum);
    }

    public int getCoinTotalValue()
    {
        return this.monies.stream().filter(Coin.class::isInstance).map(Money::getValue).reduce(0, Integer::sum);
    }

    public int getBillTotalValue()
    {
        return this.monies.stream().filter(Bill.class::isInstance).map(Money::getValue).reduce(0, Integer::sum);
    }
}
