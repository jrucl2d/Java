package tdd.stringcalculator;

public class StringCalculator {
    public static int splitAndSum(String string) {
        if (string == null || string.isEmpty()) return 0;
        String[] split = string.split("[,;]");
        int ret = 0;
        for (String s : split) {
            ret += Integer.parseInt(s);
        }
        return ret;
    }
}
