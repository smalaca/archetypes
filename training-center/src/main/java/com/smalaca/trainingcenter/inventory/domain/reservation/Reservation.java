package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.ReservationRequest;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingSessionId;

import java.time.LocalDateTime;
import java.util.UUID;

@DomainDrivenDesign.AggregateRoot
@ArchetypeAvailability.Reservation
public class Reservation {
    private final ReservationId reservationId;
    private final TrainingSessionId trainingSessionId;
    private final ParticipantId participantId;
    private final LocalDateTime reservedAt;
    private final ReservationStatus status;

    private Reservation(
            ReservationId reservationId, TrainingSessionId trainingSessionId,
            ParticipantId participantId, LocalDateTime reservedAt, ReservationStatus status) {
        this.reservationId = reservationId;
        this.trainingSessionId = trainingSessionId;
        this.participantId = participantId;
        this.reservedAt = reservedAt;
        this.status = status;
    }

    @DomainDrivenDesign.Factory
    public static Reservation confirmed(ReservationRequest request) {
        return new Reservation(
                new ReservationId(UUID.randomUUID()),
                request.trainingSessionId(),
                request.participantId(),
                request.requestedAt(),
                ReservationStatus.CONFIRMED);
    }
}
