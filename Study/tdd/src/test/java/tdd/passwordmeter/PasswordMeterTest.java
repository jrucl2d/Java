package tdd.passwordmeter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMeterTest {

    @Test
    void nullInput() {
        var meter = new PasswordMeter();
        var result = meter.meter(null);
        assertThat(result).isEqualTo(PasswordStrength.INVALID);
    }

    @Test
    void emptyInput() {
        var meter = new PasswordMeter();
        var result = meter.meter("");
        assertThat(result).isEqualTo(PasswordStrength.INVALID);
    }
}
