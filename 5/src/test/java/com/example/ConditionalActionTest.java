package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ConditionalActionTest {
    @Test
    void inspectOneConditionEvaluatesTrue() {
        final Facts facts = new Facts();
        facts.addFact("jobTitle", "CEO");
        final ConditionalAction conditionalAction = new JobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);

        final List<Report> reportList = inspector.inspect(facts);

        assertThat(reportList.size()).isOne();
        assertThat(reportList.get(0).isPositive()).isTrue();
    }

    private static class JobTitleCondition implements ConditionalAction {

        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFact("jobTitle"));
        }

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException();
        }
    }
}