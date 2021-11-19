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

    @Test
    void meet2RulesExceptForLengthRule() {
        assertPasswordStrength("abc12AB", PasswordStrength.NORMAL);
        assertPasswordStrength("12ABabc", PasswordStrength.NORMAL);
    }

    @Test
    void meet2RulesExceptForDigitRule() {
        assertPasswordStrength("abcdABabc", PasswordStrength.NORMAL);
    }

    @Test
    void meet2RulesExceptForUppercaseRule() {
        assertPasswordStrength("abcde1234", PasswordStrength.NORMAL);
    }

    @Test
    void meetOnlyLengthRule() {
        assertPasswordStrength("abcdefgwiegc", PasswordStrength.WEEK);
    }

    @Test
    void meetOnlyDigitRule() {
        assertPasswordStrength("123", PasswordStrength.WEEK);
    }

    @Test
    void meetOnlyUppercaseRule() {
        assertPasswordStrength("WBEWEWF", PasswordStrength.WEEK);
    }

    @Test
    void noRules() {
        assertPasswordStrength("ab", PasswordStrength.WEEK);
    }

    private void assertPasswordStrength(String password, PasswordStrength expected) {
        var meter = new PasswordMeter();
        var result = meter.meter(password);
        assertThat(result).isEqualTo(expected);
    }
}
