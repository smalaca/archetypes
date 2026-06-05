package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.List;

@DomainDrivenDesign.Policy
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
