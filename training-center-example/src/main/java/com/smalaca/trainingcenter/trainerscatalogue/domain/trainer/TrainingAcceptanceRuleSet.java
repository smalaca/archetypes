package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;
import java.util.List;

@ArchetypeRule.RuleSet
class TrainingAcceptanceRuleSet {
    private final List<TrainingRule> rules;

    TrainingAcceptanceRuleSet(List<TrainingRule> rules) {
        this.rules = rules;
    }

    boolean canAccept(TrainingContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
