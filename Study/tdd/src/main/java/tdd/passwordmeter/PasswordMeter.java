package tdd.passwordmeter;

import java.util.regex.Pattern;

public class PasswordMeter {
    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty())
            return PasswordStrength.INVALID;

        var meetCount = 0;
        if (containsDigit(password)) meetCount++;
        if (containsUppercase(password)) meetCount++;
        if (password.length() >= 8) meetCount++;

        if (meetCount == 0) {
            return PasswordStrength.WEEK;
        }
        if (meetCount == 1) {
            return PasswordStrength.WEEK;
        }
        if (meetCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean containsUppercase(String password) {
        var pattern = Pattern.compile("[A-Z]");
        var matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean containsDigit(String password) {
        var pattern = Pattern.compile("[0-9]");
        var matcher = pattern.matcher(password);
        return matcher.find();
    }
}
