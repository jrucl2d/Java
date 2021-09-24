package com.example;

import java.util.ArrayList;
import java.util.List;

public class BusinessRoleEngine {
    private final List<Action> actions;

    public BusinessRoleEngine() {
        this.actions = new ArrayList<>();
    }

    public void addAction(final Action action) {
        this.actions.add(action);
    }

    public int count() {
        return this.actions.size();
    }

    public void run() {
        this.actions.forEach(Action::perform);
    }
}
