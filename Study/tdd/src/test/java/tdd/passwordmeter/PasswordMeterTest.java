package tdd.passwordmeter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMeterTest {

    @ParameterizedTest(name = "{0}일 경우 {1}")
    @MethodSource("argsMethod")
    void inputTest(String password, PasswordStrength expected) {
        var meter = new PasswordMeter();
        var result = meter.meter(password);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> argsMethod() {
        return Stream.of(
                Arguments.of(null, PasswordStrength.INVALID),
                Arguments.of("", PasswordStrength.INVALID),
                Arguments.of("abcdABCD123", PasswordStrength.STRONG),
                Arguments.of("123abcdABCD", PasswordStrength.STRONG),
                Arguments.of("ABCD1234abc", PasswordStrength.STRONG),
                Arguments.of("abc12AB", PasswordStrength.NORMAL),
                Arguments.of("12ABabc", PasswordStrength.NORMAL),
                Arguments.of("abcdABabc", PasswordStrength.NORMAL),
                Arguments.of("abcde1234", PasswordStrength.NORMAL),
                Arguments.of("abcdefgwiegc", PasswordStrength.WEEK),
                Arguments.of("123", PasswordStrength.WEEK),
                Arguments.of("WBEWEWF", PasswordStrength.WEEK),
                Arguments.of("ab", PasswordStrength.WEEK)
                );
    }
}
