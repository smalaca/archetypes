package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDateTime;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
@ArchetypeAvailability.CancellationPolicy
class CancellationWindowOpenRule implements TrainingCancellationRule {
    private static final int DAYS_BEFORE_TRAINING = 7;

    @Override
    public boolean isSatisfiedBy(TrainingCancellationContext context) {
        LocalDateTime weekAfterRequested = context.cancellationRequestedAt().plusDays(DAYS_BEFORE_TRAINING);

        return weekAfterRequested.isBefore(context.trainingStartsAt()) || weekAfterRequested.isEqual(context.trainingStartsAt());
    }
}
