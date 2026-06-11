package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;

@DomainDrivenDesign.AggregateRoot
@ArchetypeInventory.InventoryEntry
public class TrainingAvailability {
    private final TrainingAvailabilityId trainingAvailabilityId;
    private final TrainingSessionId trainingSessionId;
    private final Capacity capacity;
    private ReservedSeats reservedSeats;

    public TrainingAvailability(
            TrainingAvailabilityId trainingAvailabilityId, TrainingSessionId trainingSessionId,
            Capacity capacity, ReservedSeats reservedSeats) {
        this.trainingAvailabilityId = trainingAvailabilityId;
        this.trainingSessionId = trainingSessionId;
        this.capacity = capacity;
        this.reservedSeats = reservedSeats;
    }

    @DomainDrivenDesign.Factory
    public Reservation reserve(ReservationRequest request) {
        if (noSeatsAvailable()) {
            throw TrainingAvailabilityException.noSeatsAvailable();
        }

        reservedSeats = reservedSeats.increment();

        return Reservation.confirmed(request);
    }

    private boolean noSeatsAvailable() {
        return reservedSeats.value() >= capacity.value();
    }
}
