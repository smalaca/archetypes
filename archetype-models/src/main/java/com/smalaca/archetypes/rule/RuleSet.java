package com.smalaca.archetypes.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleSet {
    private final List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public Collection<Rule> getRules() {
        return List.copyOf(rules);
    }
}
