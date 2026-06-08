package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.AggregateRoot
@ArchetypeInventory.InventoryEntry
public class TrainingAvailability {
    private final TrainingAvailabilityId trainingAvailabilityId;
    private final TrainingSessionId trainingSessionId;
    private final Capacity capacity;
    private final ReservedSeats reservedSeats;

    public TrainingAvailability(
            TrainingAvailabilityId trainingAvailabilityId, TrainingSessionId trainingSessionId,
            Capacity capacity, ReservedSeats reservedSeats) {
        this.trainingAvailabilityId = trainingAvailabilityId;
        this.trainingSessionId = trainingSessionId;
        this.capacity = capacity;
        this.reservedSeats = reservedSeats;
    }
}
