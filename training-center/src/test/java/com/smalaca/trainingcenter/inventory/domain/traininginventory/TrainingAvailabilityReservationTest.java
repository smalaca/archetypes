package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainingAvailabilityReservationTest {
    private static final TrainingSessionId TRAINING_SESSION_ID = new TrainingSessionId(UUID.randomUUID());
    private static final LocalDateTime REQUESTED_AT = LocalDateTime.of(2026, 4, 1, 10, 0);
    private static final LocalDateTime STARTS_AT = LocalDateTime.of(2026, 6, 1, 9, 0);
    private static final LocalDateTime ENDS_AT = LocalDateTime.of(2026, 6, 1, 17, 0);

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final TrainingAvailabilityFactory factory = TrainingAvailabilityFactory.trainingAvailabilityFactory(reservationRepository);

    @Test
    void shouldCreateReservationSuccessfullyWhenAllRulesAreSatisfied() {
        TrainingAvailability availability = trainingAvailability(20);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        ParticipantId participantId = participantId();

        Reservation actual = availability.reserve(reservationRequest(participantId));

        assertThat(actual).extracting("trainingSessionId").isEqualTo(TRAINING_SESSION_ID);
        assertThat(actual).extracting("participantId").isEqualTo(participantId);
        assertThat(actual).extracting("reservationId").isNotNull();
        assertThat(actual).extracting("reservedAt").isEqualTo(REQUESTED_AT);
        assertThat(actual).extracting("status").isEqualTo(ReservationStatus.CONFIRMED);
        assertThat(actual).extracting("trainingStartsAt").isEqualTo(STARTS_AT);
        assertThat(actual).extracting("trainingEndsAt").isEqualTo(ENDS_AT);
    }

    @Test
    void shouldAddParticipantsWhenAllRulesAreSatisfied() {
        TrainingAvailability availability = trainingAvailability(20);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        ParticipantId participantIdOne = participantId();
        ParticipantId participantIdTwo = participantId();
        ParticipantId participantIdThree = participantId();

        availability.reserve(reservationRequest(participantIdOne));
        availability.reserve(reservationRequest(participantIdTwo));
        availability.reserve(reservationRequest(participantIdThree));

        assertThat(availability).extracting("participantIds").asList()
                .containsExactly(participantIdOne, participantIdTwo, participantIdThree);
    }

    @Test
    void shouldReserveSeatsSuccessfullyWhenAllRulesAreSatisfied() {
        TrainingAvailability availability = trainingAvailability(20);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);

        availability.reserve(reservationRequest());
        availability.reserve(reservationRequest());
        availability.reserve(reservationRequest());

        assertReservedSeats(availability, 3);
    }

    @Test
    void shouldRejectReservationWhenAvailableSeatsRuleFails() {
        TrainingAvailability availability = trainingAvailability(3);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        availability.reserve(reservationRequest());
        availability.reserve(reservationRequest());
        availability.reserve(reservationRequest());

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> availability.reserve(reservationRequest()));

        assertThat(actual).hasMessage("No seats available.");
        assertReservedSeats(availability, 3);
    }

    @Test
    void shouldRejectReservationWhenTrainingNotStartedRuleFails() {
        TrainingAvailability availability = trainingAvailability(20);
        LocalDateTime requestedAfterStart = STARTS_AT.plusHours(1);
        ReservationRequest request = new ReservationRequest(TRAINING_SESSION_ID, participantId(), requestedAfterStart, STARTS_AT, ENDS_AT);

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> availability.reserve(request));

        assertThat(actual.getMessage()).isEqualTo("No seats available.");
        assertReservedSeats(availability, 0);
    }

    @Test
    void shouldRejectReservationWhenParticipantHasScheduleConflictRuleFails() {
        TrainingAvailability availability = trainingAvailability(20);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(false);
        ReservationRequest request = reservationRequest();

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> availability.reserve(request));

        assertThat(actual.getMessage()).isEqualTo("No seats available.");
        assertReservedSeats(availability, 0);
    }

    private ReservationRequest reservationRequest() {
        return reservationRequest(participantId());
    }

    private static ReservationRequest reservationRequest(ParticipantId participantId) {
        return new ReservationRequest(TRAINING_SESSION_ID, participantId, REQUESTED_AT, STARTS_AT, ENDS_AT);
    }

    private ParticipantId participantId() {
        return new ParticipantId(UUID.randomUUID());
    }

    private TrainingAvailability trainingAvailability(int capacity) {
        return factory.create(TRAINING_SESSION_ID, new Capacity(capacity));
    }

    private void assertReservedSeats(TrainingAvailability availability, int expected) {
        assertThat(availability).extracting("reservedSeats").extracting("value").isEqualTo(expected);
    }
}
