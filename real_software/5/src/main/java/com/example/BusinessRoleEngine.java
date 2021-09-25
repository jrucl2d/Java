package com.example;

import java.util.ArrayList;
import java.util.List;

public class BusinessRoleEngine {
    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRoleEngine(final Facts facts) {
        this.rules = new ArrayList<>();
        this.facts = facts;
    }

    public void addRule(final Rule rule) {
        this.rules.add(rule);
    }

    public int count() {
        return this.rules.size();
    }

    public void run() {
        this.rules.forEach(action -> action.perform(facts));
    }
}
