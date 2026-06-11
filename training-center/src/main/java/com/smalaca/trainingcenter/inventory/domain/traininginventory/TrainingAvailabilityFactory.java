package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;

import java.util.List;

@DomainDrivenDesign.Factory
public class TrainingAvailabilityFactory {
    private final TrainingEnrollmentPolicy trainingEnrollmentPolicy;

    private TrainingAvailabilityFactory(TrainingEnrollmentPolicy trainingEnrollmentPolicy) {
        this.trainingEnrollmentPolicy = trainingEnrollmentPolicy;
    }

    public static TrainingAvailabilityFactory trainingAvailabilityFactory(ReservationRepository reservationRepository) {
        TrainingEnrollmentPolicy trainingEnrollmentPolicy = new TrainingEnrollmentPolicy(List.of(
                new AvailableSeatsRule(),
                new TrainingNotStartedRule(),
                new ParticipantHasNoScheduleConflictRule(reservationRepository)
        ));
        return new TrainingAvailabilityFactory(trainingEnrollmentPolicy);
    }

    public TrainingAvailability create(TrainingSessionId trainingSessionId, Capacity capacity) {
        return new TrainingAvailability(
                TrainingAvailabilityId.trainingAvailabilityId(), trainingSessionId, capacity, new ReservedSeats(0), trainingEnrollmentPolicy);
    }
}
