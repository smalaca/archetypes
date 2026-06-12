package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class TrainingAvailabilityReservationTest {
    private static final UUID TRAINING_AVAILABILITY_ID = UUID.randomUUID();
    private static final TrainingSessionId TRAINING_SESSION_ID = new TrainingSessionId(UUID.randomUUID());
    private static final LocalDateTime REQUESTED_AT = LocalDateTime.of(2026, 4, 1, 10, 0);
    private static final LocalDateTime STARTS_AT = LocalDateTime.of(2026, 6, 1, 9, 0);
    private static final LocalDateTime ENDS_AT = LocalDateTime.of(2026, 6, 1, 17, 0);

    private final TrainingAvailabilityRepository trainingAvailabilityRepository = mock(TrainingAvailabilityRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final TrainingAvailabilityApplicationService service = TrainingAvailabilityApplicationService.create(trainingAvailabilityRepository, reservationRepository);

    @Test
    void shouldCreateReservationSuccessfullyWhenAllRulesAreSatisfied() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(20));
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        UUID participantId = UUID.randomUUID();

        service.reserve(reserveTrainingCommand(participantId));

        Reservation actual = thenSavedReservation();
        assertThat(actual).extracting("trainingSessionId").isEqualTo(TRAINING_SESSION_ID);
        assertThat(actual).extracting("participantId").isEqualTo(new ParticipantId(participantId));
        assertThat(actual).extracting("reservationId").isNotNull();
        assertThat(actual).extracting("reservedAt").isEqualTo(REQUESTED_AT);
        assertThat(actual).extracting("status").isEqualTo(ReservationStatus.CONFIRMED);
        assertThat(actual).extracting("trainingStartsAt").isEqualTo(STARTS_AT);
        assertThat(actual).extracting("trainingEndsAt").isEqualTo(ENDS_AT);
    }

    private Reservation thenSavedReservation() {
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        then(reservationRepository).should().save(captor.capture());
        return captor.getValue();
    }

    @Test
    void shouldAddParticipantsWhenAllRulesAreSatisfied() {
        TrainingAvailability availability = trainingAvailability(20);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        UUID participantIdOne = UUID.randomUUID();
        UUID participantIdTwo = UUID.randomUUID();
        UUID participantIdThree = UUID.randomUUID();

        service.reserve(reserveTrainingCommand(participantIdOne));
        service.reserve(reserveTrainingCommand(participantIdTwo));
        service.reserve(reserveTrainingCommand(participantIdThree));

        TrainingAvailability trainingAvailability = thenSavedTrainingAvailabilityHasReservedSeats(3);
        assertThat(trainingAvailability).extracting("participantIds").asList()
                .containsExactly(new ParticipantId(participantIdOne), new ParticipantId(participantIdTwo), new ParticipantId(participantIdThree));
    }

    @Test
    void shouldReserveSeatsSuccessfullyWhenAllRulesAreSatisfied() {
        TrainingAvailability availability = trainingAvailability(20);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);

        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());

        thenSavedTrainingAvailabilityHasReservedSeats(3);
    }

    @Test
    void shouldRejectReservationWhenAvailableSeatsRuleFails() {
        TrainingAvailability availability = trainingAvailability(3);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> service.reserve(reserveTrainingCommand()));

        assertThat(actual).hasMessage("No seats available.");
        assertReservedSeats(availability, 3);
        then(trainingAvailabilityRepository).should(times(3)).save(availability);
    }

    @Test
    void shouldRejectReservationWhenTrainingNotStartedRuleFails() {
        TrainingAvailability availability = trainingAvailability(20);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        LocalDateTime requestedAfterStart = STARTS_AT.plusHours(1);
        ReserveTrainingCommand command = new ReserveTrainingCommand(TRAINING_AVAILABILITY_ID, TRAINING_SESSION_ID.id(), UUID.randomUUID(), requestedAfterStart, STARTS_AT, ENDS_AT);

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> service.reserve(command));

        assertThat(actual.getMessage()).isEqualTo("No seats available.");

        assertReservedSeats(availability, 0);
        thenTrainingAvailabilityWasNotSaved();
    }

    @Test
    void shouldRejectReservationWhenParticipantHasScheduleConflictRuleFails() {
        TrainingAvailability availability = trainingAvailability(20);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(false);
        ReserveTrainingCommand command = reserveTrainingCommand();

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> service.reserve(command));

        assertThat(actual.getMessage()).isEqualTo("No seats available.");

        assertReservedSeats(availability, 0);
        thenTrainingAvailabilityWasNotSaved();
    }

    private TrainingAvailability thenSavedTrainingAvailabilityHasReservedSeats(int expected) {
        TrainingAvailability availability = thenSavedTrainingAvailability(expected);
        assertReservedSeats(availability, expected);

        return availability;
    }

    private void thenTrainingAvailabilityWasNotSaved() {
        then(trainingAvailabilityRepository).should(never()).save(any());
    }

    private TrainingAvailability thenSavedTrainingAvailability() {
        return thenSavedTrainingAvailability(1);
    }

    private TrainingAvailability thenSavedTrainingAvailability(int wantedNumberOfInvocations) {
        ArgumentCaptor<TrainingAvailability> captor = ArgumentCaptor.forClass(TrainingAvailability.class);
        then(trainingAvailabilityRepository).should(times(wantedNumberOfInvocations)).save(captor.capture());
        return captor.getValue();
    }

    private ReserveTrainingCommand reserveTrainingCommand() {
        return reserveTrainingCommand(UUID.randomUUID());
    }

    private ReserveTrainingCommand reserveTrainingCommand(UUID participantId) {
        return new ReserveTrainingCommand(TRAINING_AVAILABILITY_ID, TRAINING_SESSION_ID.id(), participantId, REQUESTED_AT, STARTS_AT, ENDS_AT);
    }

    private TrainingAvailability trainingAvailability(int capacity) {
        return TrainingAvailabilityFactory.trainingAvailabilityFactory(reservationRepository)
                .create(TRAINING_SESSION_ID, new Capacity(capacity));
    }

    private void assertReservedSeats(TrainingAvailability availability, int expected) {
        assertThat(availability).extracting("reservedSeats").extracting("value").isEqualTo(expected);
    }
}
