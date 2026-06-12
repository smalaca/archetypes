package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;

import java.util.List;

@ArchetypeAvailability.CancellationPolicy
@ArchetypeRule.RuleSet
class TrainingCancellationPolicy {
    private final List<TrainingCancellationRule> rules;

    private TrainingCancellationPolicy(List<TrainingCancellationRule> rules) {
        this.rules = rules;
    }

    static TrainingCancellationPolicy create() {
        return new TrainingCancellationPolicy(List.of(
                new CancellationWindowOpenRule(),
                new ReservationConfirmedRule(),
                new ReservationNotCancelledRule(),
                new ReservationNotCompletedRule(),
                new TrainingNotFinishedRule(),
                new TrainingNotStartedRule()
        ));
    }

    boolean allows(TrainingCancellationContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
