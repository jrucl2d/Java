package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine
{
    private final List<Drink> drinks;
    private final MoneyBucket moneyBucket;
    private final MoneyBucket insertedMoneyBucket = MoneyBucket.emptyBucket();

    public VendingMachine() {
        this(new ArrayList<>(), MoneyBucket.emptyBucket());
    }

    public VendingMachine(List<Drink> drinks) {
        this(drinks, MoneyBucket.emptyBucket());
    }

    public VendingMachine(List<Drink> drinks, int monies) {
        this(drinks, MoneyBucket.of(monies));
    }

    private VendingMachine(List<Drink> drinks, MoneyBucket moneyBucket) {
        this.drinks = drinks;
        this.moneyBucket = moneyBucket;
    }

    public List<Drink> getAllDrinks()
    {
        return this.drinks;
    }

    public void insertCoin(int coin)
    {
        insertedMoneyBucket.putCoin(coin);
    }

    public void insertBill(int bill)
    {
        insertedMoneyBucket.putBill(bill);
    }

    public void insertMoney(int money)
    {
        insertedMoneyBucket.putMoney(money);
    }

    public int getInsertedCoinValue()
    {
        return insertedMoneyBucket.getCoinTotalValue();
    }

    public int getInsertedBillValue()
    {
        return insertedMoneyBucket.getBillTotalValue();
    }

    public int getTotalInsertedMoneyValue()
    {
        return getInsertedBillValue() + getInsertedCoinValue();
    }

    public int getTotalMachineMoneyValue()
    {
        return getInsertedBillValue() + getInsertedCoinValue() + moneyBucket.getTotalValue();
    }
}
