package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.trainingcenter.inventory.domain.reservation.ParticipantId;

import java.time.LocalDateTime;

@ArchetypeAvailability.ReservationRequest
public record ReservationRequest(
        TrainingSessionId trainingSessionId, ParticipantId participantId, LocalDateTime requestedAt) {
}
