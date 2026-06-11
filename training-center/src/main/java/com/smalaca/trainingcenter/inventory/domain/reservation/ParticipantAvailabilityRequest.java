package com.smalaca.trainingcenter.inventory.domain.reservation;

import java.time.LocalDateTime;

public record ParticipantAvailabilityRequest(
        ParticipantId participantId, LocalDateTime startsAt, LocalDateTime endsAt) {
}
