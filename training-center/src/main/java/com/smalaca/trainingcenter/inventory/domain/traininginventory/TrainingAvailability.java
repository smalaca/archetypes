package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeInventory.InventoryEntry
@ArchetypeAvailability.Availability
public class TrainingAvailability {
    private final TrainingAvailabilityId trainingAvailabilityId;
    private final TrainingSessionId trainingSessionId;
    private final Capacity capacity;
    private ReservedSeats reservedSeats;
    private final TrainingEnrollmentPolicy enrollmentPolicy;
    private final List<ParticipantId> participantIds = new ArrayList<>();

    TrainingAvailability(
            TrainingAvailabilityId trainingAvailabilityId, TrainingSessionId trainingSessionId,
            Capacity capacity, ReservedSeats reservedSeats, TrainingEnrollmentPolicy enrollmentPolicy) {
        this.trainingAvailabilityId = trainingAvailabilityId;
        this.trainingSessionId = trainingSessionId;
        this.capacity = capacity;
        this.reservedSeats = reservedSeats;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    @DomainDrivenDesign.Factory
    public Reservation reserve(ReservationRequest request) {
        if (doesNotAllowReserveFor(request)) {
            return Reservation.rejected(request);
        }

        reservedSeats = reservedSeats.increment();
        participantIds.add(request.participantId());

        return Reservation.confirmed(request);
    }

    private boolean doesNotAllowReserveFor(ReservationRequest request) {
        return !enrollmentPolicy.allows(asContext(request));
    }

    private TrainingEnrollmentContext asContext(ReservationRequest request) {
        return new TrainingEnrollmentContext(
                trainingSessionId,
                request.participantId(),
                request.requestedAt(),
                request.startsAt(),
                request.endsAt(),
                capacity.value(),
                reservedSeats.value()
        );
    }

    public void releaseSeat(ParticipantId participantId) {
        reservedSeats = reservedSeats.decrement();
        participantIds.remove(participantId);
    }

    public TrainingAvailabilityId getTrainingAvailabilityId() {
        return trainingAvailabilityId;
    }
}
