package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;

import java.time.LocalDateTime;

@ArchetypeAvailability.ReservationRequest
public record ReservationRequest(
        TrainingSessionId trainingSessionId, ParticipantId participantId, LocalDateTime requestedAt,
        LocalDateTime startsAt, LocalDateTime endsAt) {
}
