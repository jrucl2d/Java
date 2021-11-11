package tdd.stringcalculator;

public class StringCalculator {
    public static int splitAndSum(String string) {
        if (isBlank(string)) return 0;
        return sum(toInts(string.split("[,;]")));
    }

    private static boolean isBlank(String string) {
        if (string == null || string.isEmpty())
            return true;
        return false;
    }

    private static int[] toInts(String[] values) {
        int[] ret = new int[values.length];
        int i = 0;
        for (String value : values) {
            ret[i++] = Integer.parseInt(value);
        }
        return ret;
    }

    private static int sum(int[] numbers) {
        int ret = 0;
        for (int number : numbers) {
            ret += number;
        }
        return ret;
    }
}
