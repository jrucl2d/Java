package com.example;

import java.util.ArrayList;
import java.util.List;

public class BusinessRoleEngine {
    private final List<Action> actions;
    private final Facts facts;

    public BusinessRoleEngine(final Facts facts) {
        this.actions = new ArrayList<>();
        this.facts = facts;
    }

    public void addAction(final Action action) {
        this.actions.add(action);
    }

    public int count() {
        return this.actions.size();
    }

    public void run() {
        this.actions.forEach(action -> action.perform(facts));
    }
}
