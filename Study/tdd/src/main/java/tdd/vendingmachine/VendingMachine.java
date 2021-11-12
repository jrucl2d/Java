package tdd.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine
{
    private final List<String> drinks;
    private final CoinBucket coinBucket;
    private final CoinBucket insertedCoinBucket = CoinBucket.emptyBucket();
    private List<Bill> bills = new ArrayList<>();
    private List<Bill> insertedBills = new ArrayList<>();

    public VendingMachine() {
        this(new ArrayList<>(), CoinBucket.emptyBucket());
    }

    public VendingMachine(List<String> drinks) {
        this(drinks, CoinBucket.emptyBucket());
    }

    public VendingMachine(List<String> drinks, List<Integer> coins) {
        this(drinks, CoinBucket.of(coins));
    }

    private VendingMachine(List<String> drinks, CoinBucket coinBucket) {
        this.drinks = drinks;
        this.coinBucket = coinBucket;
    }

    public List<String> getAllDrinks()
    {
        return this.drinks;
    }

    public void insertCoin(int coin)
    {
        insertedCoinBucket.putCoin(coin);
    }

    public int getInsertedCoinValue()
    {
        return insertedCoinBucket.getTotalValue();
    }

    public int getTotalCoinValue()
    {
        return insertedCoinBucket.getTotalValue() + coinBucket.getTotalValue();
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
