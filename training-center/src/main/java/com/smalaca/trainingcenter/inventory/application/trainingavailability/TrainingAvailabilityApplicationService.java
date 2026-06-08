package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.Capacity;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.ReservedSeats;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailability;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityId;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingSessionId;

import java.util.UUID;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeInventory.Inventory
public class TrainingAvailabilityApplicationService {
    private final TrainingAvailabilityRepository repository;

    public TrainingAvailabilityApplicationService(TrainingAvailabilityRepository repository) {
        this.repository = repository;
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingAvailabilityId createTrainingAvailability(CreateTrainingAvailabilityCommand command) {
        TrainingAvailabilityId id = new TrainingAvailabilityId(UUID.randomUUID());
        TrainingSessionId trainingSessionId = new TrainingSessionId(command.trainingSessionId());
        Capacity capacity = new Capacity(command.capacity());
        ReservedSeats reservedSeats = new ReservedSeats(command.reservedSeats());

        TrainingAvailability trainingAvailability = new TrainingAvailability(id, trainingSessionId, capacity, reservedSeats);

        repository.save(trainingAvailability);
        return id;
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void removeTrainingAvailability(RemoveTrainingAvailabilityCommand command) {
        TrainingAvailabilityId id = new TrainingAvailabilityId(command.trainingAvailabilityId());

        repository.delete(id);
    }
}
