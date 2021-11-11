package tdd.stringcalculator;

public class Positive {
    private final int number;

    public Positive(int nvalue) {
        if (nvalue < 0)
            throw new RuntimeException();
        this.number = nvalue;
    }

    public Positive(String value) {
        this(Integer.parseInt(value));
    }

    public Positive add(Positive aval) {
        return new Positive(this.number + aval.number);
    }

    public int getNumber() {
        return number;
    }
}
