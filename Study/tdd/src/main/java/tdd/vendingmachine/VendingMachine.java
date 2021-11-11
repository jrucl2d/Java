package tdd.vendingmachine;

import java.util.Collections;
import java.util.List;

public class VendingMachine
{
    private final List<String> drinks;

    public VendingMachine() {
        this.drinks = Collections.emptyList();
    }
    public VendingMachine(List<String> drinks) {
        this.drinks = drinks;
    }

    public List<String> getAllDrinks()
    {
        return this.drinks;
    }
}
