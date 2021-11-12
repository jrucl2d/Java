package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine
{
    private final List<String> drinks;
    private final MoneyBucket moneyBucket;
    private final MoneyBucket insertedMoneyBucket = MoneyBucket.emptyBucket();
    private final MoneyBucket
    private List<Bill> bills = new ArrayList<>();
    private List<Bill> insertedBills = new ArrayList<>();

    public VendingMachine() {
        this(new ArrayList<>(), MoneyBucket.emptyBucket());
    }

    public VendingMachine(List<String> drinks) {
        this(drinks, MoneyBucket.emptyBucket());
    }

    public VendingMachine(List<String> drinks, List<Integer> coins) {
        this(drinks, MoneyBucket.of(coins));
    }

    private VendingMachine(List<String> drinks, MoneyBucket moneyBucket) {
        this.drinks = drinks;
        this.moneyBucket = moneyBucket;
    }

    public List<String> getAllDrinks()
    {
        return this.drinks;
    }

    public void insertCoin(int coin)
    {
        insertedMoneyBucket.putCoin(coin);
    }

    public int getInsertedCoinValue()
    {
        return insertedMoneyBucket.getTotalValue();
    }

    public int getTotalCoinValue()
    {
        return insertedMoneyBucket.getTotalValue() + moneyBucket.getTotalValue();
    }

    public void insertBill(int bill)
    {
        insertedBills.add(new Bill(bill));
    }

    public int getInsertedBillValue()
    {
        return insertedBills.stream().map(Bill::getValue).reduce(0, Integer::sum);
    }

    public int getTotalBillValue()
    {
        return bills.stream().map(Bill::getValue).reduce(0, Integer::sum) + getInsertedBillValue();
    }
}
