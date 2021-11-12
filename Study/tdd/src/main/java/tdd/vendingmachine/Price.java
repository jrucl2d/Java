package tdd.vendingmachine;

public enum Price
{
    오십원(50),
    백원(100),
    오백원(500),
    천원(1_000),
    오천원(5_000),
    만원(10_000),
    오만원(50_000)
    ;

    private final int value;

    public int getValue()
    {
        return value;
    }

    Price(int value)
    {
        this.value = value;
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
        return this != 오십원 && this != 백원 && this != 오백원;
    }
}
