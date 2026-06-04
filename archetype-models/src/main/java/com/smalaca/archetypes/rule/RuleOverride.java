package com.smalaca.archetypes.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleOverride {
    private final Rule rule;
    private final RuleContext context;
    private final List<PartySignature> signatures = new ArrayList<>();

    public RuleOverride(Rule rule, RuleContext context) {
        this.rule = rule;
        this.context = context;
    }

    public void addSignature(PartySignature signature) {
        signatures.add(signature);
    }

    public Rule getRule() {
        return rule;
    }

    public RuleContext getContext() {
        return context;
    }

    public Collection<PartySignature> getSignatures() {
        return List.copyOf(signatures);
    }
}
