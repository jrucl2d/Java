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
        totalValue = moneyBucket.calculateBill(totalValue);
        totalValue = moneyBucket.calculateCoin(totalValue);
        if (totalValue != 0)
            throw new IllegalArgumentException();
        return moneyBucket;
    }

    private int calculateBill(int totalValue) {
        return calculateMoney(totalValue, 0, 4, this::putBill);
    }
    private int calculateCoin(int totalValue) {
        return calculateMoney(totalValue, 4, 7, this::putCoin);
    }

    private int calculateMoney(int totalValue, int from, int to, IntConsumer putFunction)
    {
        List<Integer> possibles = Price.getValues();
        for (int i = from; i < to; i++) {
            int money = possibles.get(i);
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
        money = calculateBill(money);
        money = calculateCoin(money);
        if (money != 0)
            throw new IllegalArgumentException();
    }

    public void putCoin(int coin)
    {
        this.monies.add(new Coin(coin));
    }

    public void putBill(int bill)
    {
        this.monies.add(new Bill(bill));
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
