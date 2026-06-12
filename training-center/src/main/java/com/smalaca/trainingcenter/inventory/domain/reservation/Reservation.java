package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
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
    private final LocalDateTime trainingStartsAt;
    private final LocalDateTime trainingEndsAt;
    private ReservationStatus status;

    private Reservation(
            ReservationId reservationId, TrainingSessionId trainingSessionId,
            ParticipantId participantId, LocalDateTime reservedAt,
            LocalDateTime trainingStartsAt, LocalDateTime trainingEndsAt, ReservationStatus status) {
        this.reservationId = reservationId;
        this.trainingSessionId = trainingSessionId;
        this.participantId = participantId;
        this.reservedAt = reservedAt;
        this.trainingStartsAt = trainingStartsAt;
        this.trainingEndsAt = trainingEndsAt;
        this.status = status;
    }

    @DomainDrivenDesign.Factory
    public static Reservation confirmed(ReservationRequest request) {
        return new Reservation(
                new ReservationId(UUID.randomUUID()),
                request.trainingSessionId(),
                request.participantId(),
                request.requestedAt(),
                request.startsAt(),
                request.endsAt(),
                ReservationStatus.CONFIRMED);
    }

    public void cancel() {
        status = ReservationStatus.CANCELLED;
    }

    public ReservationDto asReservationDto() {
        return new ReservationDto(status, participantId, trainingStartsAt, trainingEndsAt);
    }
}
