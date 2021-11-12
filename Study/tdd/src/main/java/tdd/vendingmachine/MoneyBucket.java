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
        moneyBucket.calculateMoney(totalValue, 0, 4, moneyBucket::putBill);
        moneyBucket.calculateMoney(totalValue, 4, 7, moneyBucket::putCoin);
        return moneyBucket;
    }

    private void calculateMoney(int totalValue, int from, int to, IntConsumer putFunction)
    {
        int[] possibles = {50000, 10000, 5000, 1000, 500, 100, 50};
        for (int i = from; i < to; i++) {
            int money = possibles[i];
            int count = totalValue / money;
            totalValue -= money * count;
            for (int j = 0; j < count; j++) {
                putFunction.accept(money);
            }
        }
    }

    public void putCoin(int coin)
    {
        this.monies.add(new Coin(coin));
    }

    public int getTotalValue()
    {
        return this.monies.stream().map(Money::getValue).reduce(0, Integer::sum);
    }

    public void putBill(int bill)
    {
        this.monies.add(new Bill(bill));
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
