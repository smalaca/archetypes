package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.reservation.ParticipantAvailabilityRequest;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
class ParticipantHasNoScheduleConflictRule implements TrainingEnrollmentRule {
    private final ReservationRepository reservationRepository;

    ParticipantHasNoScheduleConflictRule(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public boolean isSatisfiedBy(TrainingEnrollmentContext context) {
        ParticipantAvailabilityRequest request = new ParticipantAvailabilityRequest(
                context.participantId(), context.startsAt(), context.endsAt());

        return reservationRepository.hasNoScheduleConflict(request);
    }
}
