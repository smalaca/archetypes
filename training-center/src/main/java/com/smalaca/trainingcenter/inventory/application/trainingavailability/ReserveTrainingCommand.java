package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.ReservationRequest;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingSessionId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReserveTrainingCommand(
        UUID trainingAvailabilityId, UUID trainingSessionId, UUID participantId, LocalDateTime requestedAt,
        LocalDateTime startsAt, LocalDateTime endsAt) {
    ReservationRequest asReservationRequest() {
        return new ReservationRequest(
                new TrainingSessionId(trainingSessionId()), new ParticipantId(participantId()), requestedAt(), startsAt(), endsAt());
    }
}
