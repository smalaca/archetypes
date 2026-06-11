package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
class TrainingNotStartedRule implements TrainingEnrollmentRule {
    @Override
    public boolean isSatisfiedBy(TrainingEnrollmentContext context) {
        return context.requestedAt().isBefore(context.startsAt());
    }
}
