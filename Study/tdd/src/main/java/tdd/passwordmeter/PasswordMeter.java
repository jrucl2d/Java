package tdd.passwordmeter;

public class PasswordMeter {
    public PasswordStrength meter(String password) {
        if (password == null)
            return PasswordStrength.INVALID;
        if (password.isEmpty())
            return PasswordStrength.INVALID;
        return PasswordStrength.STRONG;
    }
}
