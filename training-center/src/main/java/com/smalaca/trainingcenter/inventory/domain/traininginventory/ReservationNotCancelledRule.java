package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
@ArchetypeAvailability.CancellationPolicy
class ReservationNotCancelledRule implements TrainingCancellationRule {
    @Override
    public boolean isSatisfiedBy(TrainingCancellationContext context) {
        return !ReservationStatus.CANCELLED.equals(context.reservationStatus());
    }
}
