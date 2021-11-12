package tdd.vendingmachine;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Price
{
    오십원(50, PriceType.COIN),
    백원(100, PriceType.COIN),
    오백원(500, PriceType.COIN),
    천원(1_000, PriceType.BILL),
    오천원(5_000, PriceType.BILL),
    만원(10_000, PriceType.BILL),
    오만원(50_000, PriceType.BILL)
    ;

    private final int value;
    private final PriceType priceType;

    public int getValue()
    {
        return value;
    }
    public static List<Integer> getValues()
    {
        return Arrays.stream(values())
            .map(Price::getValue)
            .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    Price(int value, PriceType priceType)
    {
        this.value = value;
        this.priceType = priceType;
    }

    public static Price of(int value)
    {
        switch (value)
        {
            case 50:
                return 오십원;
            case 100:
                return 백원;
            case 500:
                return 오백원;
            case 1_000:
                return 천원;
            case 5_000:
                return 오천원;
            case 10_000:
                return 만원;
            case 50_000:
                return 오만원;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Price ofCoin(int value)
    {
        Price price = of(value);
        if (price.isBill())
            throw new IllegalArgumentException();
        return price;
    }

    public static Price ofBill(int value)
    {
        Price price = of(value);
        if (!price.isBill())
            throw new IllegalArgumentException();
        return price;
    }

    private boolean isBill()
    {
        return this.priceType == PriceType.BILL;
    }

    public enum PriceType
    {
        COIN,
        BILL
    }
}
