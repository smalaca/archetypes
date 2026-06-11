package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.*;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeInventory.Inventory
public class TrainingAvailabilityApplicationService {
    private final TrainingAvailabilityRepository repository;
    private final TrainingAvailabilityFactory factory;

    private TrainingAvailabilityApplicationService(TrainingAvailabilityRepository repository, TrainingAvailabilityFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public static TrainingAvailabilityApplicationService create(
            TrainingAvailabilityRepository trainingAvailabilityRepository, ReservationRepository reservationRepository) {
        return new TrainingAvailabilityApplicationService(
                trainingAvailabilityRepository, TrainingAvailabilityFactory.trainingAvailabilityFactory(reservationRepository));
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingAvailabilityId createTrainingAvailability(CreateTrainingAvailabilityCommand command) {
        TrainingSessionId trainingSessionId = new TrainingSessionId(command.trainingSessionId());
        Capacity capacity = new Capacity(command.capacity());

        TrainingAvailability trainingAvailability = factory.create(trainingSessionId, capacity);

        repository.save(trainingAvailability);
        return trainingAvailability.getTrainingAvailabilityId();
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void removeTrainingAvailability(RemoveTrainingAvailabilityCommand command) {
        TrainingAvailabilityId id = new TrainingAvailabilityId(command.trainingAvailabilityId());

        repository.delete(id);
    }
}
