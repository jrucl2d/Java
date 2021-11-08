package tdd.prac;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueryStringTest {
    @Test
    void testOneNameValuePair() {
        QueryString qs = new QueryString("name=value");
        assertThat(qs.count()).isEqualTo(1);
    }

    @Test
    void testNoNameValuePairs() {
        QueryString qs = new QueryString("");
        assertThat(qs.count()).isZero();
    }

    @Test
    void testNull() {
        try {
            QueryString qs = new QueryString(null);
            Assertions.fail("Should throw exception");
        } catch (NullPointerException e) {
            // expected
        }
    }
}