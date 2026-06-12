package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationAssertion;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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

        thenSavedReservation()
                .hasTrainingSessionIdEqualTo(TRAINING_SESSION_ID)
                .hasParticipantIdEqualTo(new ParticipantId(participantId))
                .hasReservationId()
                .hasReservedAtEqualTo(REQUESTED_AT)
                .isConfirmed()
                .hasTrainingStartsAtEqualTo(STARTS_AT)
                .hasTrainingEndsAtEqualTo(ENDS_AT);
    }

    @Test
    void shouldAddParticipantsWhenAllRulesAreSatisfied() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(20));
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        UUID participantIdOne = UUID.randomUUID();
        UUID participantIdTwo = UUID.randomUUID();
        UUID participantIdThree = UUID.randomUUID();

        service.reserve(reserveTrainingCommand(participantIdOne));
        service.reserve(reserveTrainingCommand(participantIdTwo));
        service.reserve(reserveTrainingCommand(participantIdThree));

        TrainingAvailability availability = thenSavedTrainingAvailability(3);
        assertReservedSeats(availability, 3);
        assertThat(availability).extracting("participantIds").asList()
                .containsExactly(new ParticipantId(participantIdOne), new ParticipantId(participantIdTwo), new ParticipantId(participantIdThree));
    }

    @Test
    void shouldReserveSeatsSuccessfullyWhenAllRulesAreSatisfied() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(20));
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);

        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());

        assertReservedSeats(thenSavedTrainingAvailability(3), 3);
    }

    @Test
    void shouldRejectReservationWhenAvailableSeatsRuleFails() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(3));
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(true);
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());
        service.reserve(reserveTrainingCommand());

        service.reserve(reserveTrainingCommand());

        thenSavedReservation(4).isRejected();
        assertReservedSeats(thenSavedTrainingAvailability(4), 3);
    }

    @Test
    void shouldRejectReservationWhenTrainingNotStartedRuleFails() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(20));
        LocalDateTime requestedAfterStart = STARTS_AT.plusHours(1);
        ReserveTrainingCommand command = new ReserveTrainingCommand(TRAINING_AVAILABILITY_ID, TRAINING_SESSION_ID.id(), UUID.randomUUID(), requestedAfterStart, STARTS_AT, ENDS_AT);

        service.reserve(command);

        thenSavedReservation().isRejected();
        assertReservedSeats(thenSavedTrainingAvailability(1), 0);
    }

    @Test
    void shouldRejectReservationWhenParticipantHasScheduleConflictRuleFails() {
        given(trainingAvailabilityRepository.findById(any())).willReturn(trainingAvailability(20));
        given(reservationRepository.hasNoScheduleConflict(any())).willReturn(false);
        ReserveTrainingCommand command = reserveTrainingCommand();

        service.reserve(command);

        thenSavedReservation().isRejected();
        assertReservedSeats(thenSavedTrainingAvailability(1), 0);
    }

    private ReservationAssertion thenSavedReservation() {
        return thenSavedReservation(1);
    }

    private ReservationAssertion thenSavedReservation(int wantedNumberOfInvocations) {
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        then(reservationRepository).should(times(wantedNumberOfInvocations)).save(captor.capture());
        Reservation actual = captor.getAllValues().get(wantedNumberOfInvocations - 1);

        return ReservationAssertion.assertThat(actual);
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
