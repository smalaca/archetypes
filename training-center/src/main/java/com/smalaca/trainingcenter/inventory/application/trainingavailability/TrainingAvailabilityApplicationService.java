package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.*;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeInventory.Inventory
public class TrainingAvailabilityApplicationService {
    private final TrainingAvailabilityRepository trainingAvailabilityRepository;
    private final TrainingAvailabilityFactory factory;
    private final ReservationRepository reservationRepository;

    private TrainingAvailabilityApplicationService(
            TrainingAvailabilityRepository trainingAvailabilityRepository, TrainingAvailabilityFactory factory, ReservationRepository reservationRepository) {
        this.trainingAvailabilityRepository = trainingAvailabilityRepository;
        this.factory = factory;
        this.reservationRepository = reservationRepository;
    }

    public static TrainingAvailabilityApplicationService create(
            TrainingAvailabilityRepository trainingAvailabilityRepository, ReservationRepository reservationRepository) {
        TrainingAvailabilityFactory trainingAvailabilityFactory = TrainingAvailabilityFactory.trainingAvailabilityFactory(reservationRepository);

        return new TrainingAvailabilityApplicationService(
                trainingAvailabilityRepository, trainingAvailabilityFactory, reservationRepository);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingAvailabilityId createTrainingAvailability(CreateTrainingAvailabilityCommand command) {
        TrainingSessionId trainingSessionId = new TrainingSessionId(command.trainingSessionId());
        Capacity capacity = new Capacity(command.capacity());

        TrainingAvailability trainingAvailability = factory.create(trainingSessionId, capacity);

        trainingAvailabilityRepository.save(trainingAvailability);
        return trainingAvailability.getTrainingAvailabilityId();
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    @ArchetypeInventory.Inventory
    public void reserve(ReserveTrainingCommand command) {
        TrainingAvailability trainingAvailability = trainingAvailabilityRepository.findById(new TrainingAvailabilityId(command.trainingAvailabilityId()));
        ReservationRequest reservationRequest = command.asReservationRequest();

        Reservation reservation = trainingAvailability.reserve(reservationRequest);

        trainingAvailabilityRepository.save(trainingAvailability);
        reservationRepository.save(reservation);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void removeTrainingAvailability(RemoveTrainingAvailabilityCommand command) {
        TrainingAvailabilityId id = new TrainingAvailabilityId(command.trainingAvailabilityId());

        trainingAvailabilityRepository.delete(id);
    }
}
