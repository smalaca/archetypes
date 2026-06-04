package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;
import java.util.List;

@ArchetypeRule.RuleSet
class TrainingAcceptancePolicy {
    private final List<TrainingRule> rules;

    TrainingAcceptancePolicy(List<TrainingRule> rules) {
        this.rules = rules;
    }

    boolean canAccept(TrainingContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
