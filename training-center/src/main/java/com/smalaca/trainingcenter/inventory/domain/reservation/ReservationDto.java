package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;

import java.time.LocalDateTime;

public record ReservationDto(
        ReservationStatus status, ParticipantId participantId, LocalDateTime trainingStartsAt, LocalDateTime trainingEndsAt) {
}
