package tdd.passwordmeter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMeterTest {

    @Test
    void nullInput() {
        assertPasswordStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInput() {
        assertPasswordStrength("", PasswordStrength.INVALID);
    }

    @Test
    void meetAllRules() {
        assertPasswordStrength("abcdABCD123", PasswordStrength.STRONG);
        assertPasswordStrength("123abcdABCD", PasswordStrength.STRONG);
        assertPasswordStrength("ABCD1234abc", PasswordStrength.STRONG);
    }

    private void assertPasswordStrength(String password, PasswordStrength expected) {
        var meter = new PasswordMeter();
        var result = meter.meter(password);
        assertThat(result).isEqualTo(expected);
    }
}
