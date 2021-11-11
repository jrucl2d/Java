package tdd.stringcalculator;

public class StringCalculator {
    public static int splitAndSum(String string) {
        if (isBlank(string)) return 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c != ';' && c != ',' && (c - '0' < 0 || c - '0' > 9)) throw new RuntimeException();
        }
        return sum(toInts(string.split("[,;]"))).getNumber();
    }

    private static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }

    private static Positive[] toInts(String[] values) {
        Positive[] ret = new Positive[values.length];
        int i = 0;
        for (String value : values) {
            ret[i++] = new Positive(value);
        }
        return ret;
    }

    private static Positive sum(Positive[] numbers) {
        Positive result = new Positive(0);
        for (Positive number : numbers) {
            result = result.add(number);
        }
        return result;
    }
}
