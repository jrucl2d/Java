package tdd.prac;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueryStringTest {
    @Test
    void testOneNameValuePair() {
        QueryString query = new QueryString("name=value");
        assertThat(query.count()).isEqualTo(1);
        assertThat(query.valueFor("name")).isEqualTo("value");
    }

    @Test
    void testNoNameValuePairs() {
        QueryString query = new QueryString("");
        assertThat(query.count()).isZero();
    }

    @Test
    void testMultipleValuePairs() {
        QueryString query = new QueryString("name1=value1&name2=value2&name3=value3");
        assertThat(query.count()).isEqualTo(3);
        assertThat(query.valueFor("name1")).isEqualTo("value1");
        assertThat(query.valueFor("name2")).isEqualTo("value2");
        assertThat(query.valueFor("name3")).isEqualTo("value3");
    }

    @Test
    void testNull() {
        try {
            QueryString query = new QueryString(null);
            Assertions.fail("Should throw exception");
        } catch (NullPointerException e) {
            // expected
        }
    }
}