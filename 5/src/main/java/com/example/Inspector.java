package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inspector {
    private final List<ConditionalAction> conditionalActionList;

    public Inspector(final ConditionalAction... conditionalActionList) {
        this.conditionalActionList = Arrays.asList(conditionalActionList);
    }

    public List<Report> inspect(final Facts facts) {
        final List<Report> reportList = new ArrayList<>();
        for (ConditionalAction conditionalAction : conditionalActionList) {
            final boolean conditionResult = conditionalAction.evaluate(facts);
            reportList.add(new Report(facts, conditionalAction, conditionResult));
        }
        return reportList;
    }
}
