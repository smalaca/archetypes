package com.smalaca.trainingcenter.inventory.application.reservation;

import com.smalaca.trainingcenter.inventory.domain.clock.Clock;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.*;
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

class ReservationApplicationServiceTest {
    private static final UUID TRAINING_AVAILABILITY_ID = UUID.randomUUID();
    private static final UUID RESERVATION_ID = UUID.randomUUID();
    private static final ParticipantId PARTICIPANT_ID = new ParticipantId(UUID.randomUUID());
    private static final LocalDateTime TRAINING_STARTS_AT = LocalDateTime.of(2026, 6, 10, 9, 0);
    private static final LocalDateTime TRAINING_ENDS_AT = LocalDateTime.of(2026, 6, 10, 17, 0);
    private static final LocalDateTime REQUESTED_AT = LocalDateTime.of(2026, 6, 1, 10, 0);
    private static final TrainingSessionId TRAINING_SESSION_ID = new TrainingSessionId(UUID.randomUUID());
    private static final UUID TRAINING_COORDINATOR_ID = UUID.randomUUID();
    private static final String JUSTIFICATION = "Because I can.";

    private final TrainingAvailabilityRepository trainingAvailabilityRepository = mock(TrainingAvailabilityRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final Clock clock = mock(Clock.class);
    private final ReservationApplicationService service = ReservationApplicationService.create(trainingAvailabilityRepository, reservationRepository, clock);

    @Test
    void shouldApproveRejectedReservation() {
        LocalDateTime approvedAt = LocalDateTime.of(2026, 6, 5, 12, 0);
        given(clock.now()).willReturn(approvedAt);
        Reservation reservation = Reservation.rejected(reservationRequest());
        given(reservationRepository.findById(new ReservationId(RESERVATION_ID))).willReturn(reservation);

        service.approveReservation(approveCommand());

        thenSavedReservation()
                .isConfirmed()
                .hasEnrollmentApproval(new TrainingCoordinatorId(TRAINING_COORDINATOR_ID), approvedAt, JUSTIFICATION);
    }

    @Test
    void shouldNotApproveConfirmedReservation() {
        Reservation reservation = Reservation.confirmed(reservationRequest());
        given(reservationRepository.findById(new ReservationId(RESERVATION_ID))).willReturn(reservation);

        service.approveReservation(approveCommand());

        thenSavedReservation()
                .isConfirmed()
                .hasNoEnrollmentApproval();
    }

    @Test
    void shouldNotApproveCancelledReservation() {
        Reservation reservation = Reservation.confirmed(reservationRequest());
        reservation.cancel();
        given(reservationRepository.findById(new ReservationId(RESERVATION_ID))).willReturn(reservation);

        service.approveReservation(approveCommand());

        thenSavedReservation()
                .isCancelled()
                .hasNoEnrollmentApproval();
    }

    @Test
    void shouldCancelReservationSuccessfully() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(7));
        TrainingAvailability availability = mock(TrainingAvailability.class);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        givenConfirmedReservation();

        service.cancel(command());

        thenSavedReservation().isCancelled();
        then(availability).should().releaseSeat(PARTICIPANT_ID);
        then(trainingAvailabilityRepository).should().save(availability);
    }

    @Test
    void shouldNotCancelWhenCancellationWindowIsClosed() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(7).plusNanos(1));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenConfirmedReservation();

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isConfirmed();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsNotConfirmed() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenExisting(Reservation.rejected(reservationRequest()));

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isRejected();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsAlreadyCancelled() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenConfirmedReservation();
        reservation.cancel();

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isCancelled();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsAlreadyCompleted() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenConfirmedReservation();
        reservation.complete();

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isCompleted();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenTrainingIsAlreadyFinished() {
        given(clock.now()).willReturn(TRAINING_ENDS_AT);
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenConfirmedReservation();

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isConfirmed();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenTrainingIsAlreadyStarted() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT);
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = givenConfirmedReservation();

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        ReservationAssertion.assertThat(reservation).isConfirmed();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldCompleteReservation() {
        givenConfirmedReservation();

        service.complete(RESERVATION_ID);

        thenSavedReservation().isCompleted();
    }

    private Reservation givenConfirmedReservation() {
        Reservation reservation = Reservation.confirmed(reservationRequest());
        return givenExisting(reservation);
    }

    private Reservation givenExisting(Reservation reservation) {
        given(reservationRepository.findById(any())).willReturn(reservation);
        return reservation;
    }

    private ReservationAssertion thenSavedReservation() {
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        then(reservationRepository).should().save(captor.capture());

        return ReservationAssertion.assertThat(captor.getValue());
    }

    private CancelReservationCommand command() {
        return new CancelReservationCommand(TRAINING_AVAILABILITY_ID, RESERVATION_ID);
    }

    private TrainingAvailability trainingAvailabilityWithCapacity(int value) {
        TrainingAvailabilityFactory factory = TrainingAvailabilityFactory.trainingAvailabilityFactory(reservationRepository);
        return factory.create(TRAINING_SESSION_ID, new Capacity(value));
    }

    private ReservationRequest reservationRequest() {
        return new ReservationRequest(TRAINING_SESSION_ID, PARTICIPANT_ID, REQUESTED_AT, TRAINING_STARTS_AT, TRAINING_ENDS_AT);
    }

    private ApproveReservationCommand approveCommand() {
        return new ApproveReservationCommand(RESERVATION_ID, TRAINING_COORDINATOR_ID, JUSTIFICATION);
    }
}
