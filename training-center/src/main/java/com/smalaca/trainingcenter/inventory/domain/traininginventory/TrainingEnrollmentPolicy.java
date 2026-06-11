package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;

import java.util.List;

@ArchetypeAvailability.AvailabilityPolicy
@ArchetypeRule.RuleSet
class TrainingEnrollmentPolicy {
    private final List<TrainingEnrollmentRule> rules;

    TrainingEnrollmentPolicy(List<TrainingEnrollmentRule> rules) {
        this.rules = rules;
    }

    boolean allows(TrainingEnrollmentContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
