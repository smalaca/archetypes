package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationDto;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;

import java.time.LocalDateTime;

@ArchetypeRule.RuleContext
record TrainingCancellationContext(
        ReservationStatus reservationStatus,
        ParticipantId participantId,
        LocalDateTime trainingStartsAt,
        LocalDateTime trainingEndsAt,
        LocalDateTime cancellationRequestedAt
) {
    public static TrainingCancellationContext create(ReservationDto reservationDto, LocalDateTime cancellationRequestedAt) {
        return new TrainingCancellationContext(
                reservationDto.status(), reservationDto.participantId(), reservationDto.trainingStartsAt(), reservationDto.trainingEndsAt(),
                cancellationRequestedAt);
    }
}
