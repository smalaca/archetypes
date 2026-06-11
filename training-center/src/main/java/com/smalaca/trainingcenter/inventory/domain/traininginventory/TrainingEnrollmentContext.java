package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.trainingcenter.inventory.domain.reservation.ParticipantId;

import java.time.LocalDateTime;

@ArchetypeRule.RuleContext
record TrainingEnrollmentContext(
        TrainingSessionId trainingSessionId, ParticipantId participantId, LocalDateTime requestedAt,
        LocalDateTime startsAt, LocalDateTime endsAt, int capacity, int reservedSeats) {
}
