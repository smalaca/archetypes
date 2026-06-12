package com.smalaca.trainingcenter.inventory.application.reservation;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.inventory.domain.clock.Clock;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationId;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.ReservationCancellationService;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailability;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityId;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityRepository;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeAvailability.Availability
@ArchetypeInventory.Inventory
public class ReservationApplicationService {
    private final TrainingAvailabilityRepository trainingAvailabilityRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationCancellationService reservationCancellationService;

    private ReservationApplicationService(
            TrainingAvailabilityRepository trainingAvailabilityRepository, ReservationRepository reservationRepository, ReservationCancellationService reservationCancellationService) {
        this.trainingAvailabilityRepository = trainingAvailabilityRepository;
        this.reservationRepository = reservationRepository;
        this.reservationCancellationService = reservationCancellationService;
    }

    public static ReservationApplicationService create(
            TrainingAvailabilityRepository trainingAvailabilityRepository, ReservationRepository reservationRepository, Clock clock) {
        ReservationCancellationService reservationCancellationService = ReservationCancellationService.create(clock);
        return new ReservationApplicationService(trainingAvailabilityRepository, reservationRepository, reservationCancellationService);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void cancel(CancelReservationCommand command) {
        TrainingAvailability trainingAvailability = trainingAvailabilityRepository.findById(new TrainingAvailabilityId(command.trainingAvailabilityId()));
        Reservation reservation = reservationRepository.findById(new ReservationId(command.reservationId()));

        reservationCancellationService.cancel(reservation, trainingAvailability);

        trainingAvailabilityRepository.save(trainingAvailability);
        reservationRepository.save(reservation);
    }
}
