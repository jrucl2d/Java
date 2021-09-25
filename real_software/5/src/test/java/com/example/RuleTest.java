package com.example;

import org.junit.jupiter.api.Test;

public class RuleTest {

    @Test
    void name() {
        Rule rule = RuleBuilder
            .when(facts -> "CEO".equals(facts.getFact("jobtitle")))
            .then(facts -> {
                var name = facts.getFact("name");
                System.out.println("name is " + name);
            });
    }
}
