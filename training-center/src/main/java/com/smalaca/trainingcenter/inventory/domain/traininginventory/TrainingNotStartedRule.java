package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDateTime;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
class TrainingNotStartedRule implements TrainingCancellationRule, TrainingEnrollmentRule {
    @Override
    @ArchetypeAvailability.CancellationPolicy
    public boolean isSatisfiedBy(TrainingCancellationContext context) {
        return hasTrainingStarted(context.cancellationRequestedAt(), context.trainingStartsAt());
    }

    @Override
    @ArchetypeAvailability.AvailabilityPolicy
    public boolean isSatisfiedBy(TrainingEnrollmentContext context) {
        return hasTrainingStarted(context.requestedAt(), context.startsAt());
    }

    private boolean hasTrainingStarted(LocalDateTime requestedAt, LocalDateTime trainingStartsAt) {
        return requestedAt.isBefore(trainingStartsAt);
    }
}
