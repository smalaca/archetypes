package com.smalaca.trainingcenter.inventory.application.reservation;

import com.smalaca.trainingcenter.inventory.domain.clock.Clock;
import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationDto;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailability;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class ReservationApplicationServiceTest {
    private static final UUID TRAINING_AVAILABILITY_ID = UUID.randomUUID();
    private static final UUID RESERVATION_ID = UUID.randomUUID();
    private static final ParticipantId PARTICIPANT_ID = new ParticipantId(UUID.randomUUID());
    private static final LocalDateTime TRAINING_STARTS_AT = LocalDateTime.of(2026, 6, 10, 9, 0);
    private static final LocalDateTime TRAINING_ENDS_AT = LocalDateTime.of(2026, 6, 10, 17, 0);

    private final TrainingAvailabilityRepository trainingAvailabilityRepository = mock(TrainingAvailabilityRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final Clock clock = mock(Clock.class);
    private final ReservationApplicationService service = ReservationApplicationService.create(trainingAvailabilityRepository, reservationRepository, clock);

    @Test
    void shouldCancelReservationSuccessfully() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(7));
        TrainingAvailability availability = mock(TrainingAvailability.class);
        given(trainingAvailabilityRepository.findById(any())).willReturn(availability);
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.CONFIRMED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        service.cancel(command());

        then(reservation).should().cancel();
        then(availability).should().releaseSeat(PARTICIPANT_ID);
        then(trainingAvailabilityRepository).should().save(availability);
        then(reservationRepository).should().save(reservation);
    }

    @Test
    void shouldNotCancelWhenCancellationWindowIsClosed() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(7).plusNanos(1));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.CONFIRMED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsNotConfirmed() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.PENDING, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsAlreadyCancelled() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.CANCELLED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenReservationIsAlreadyCompleted() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT.minusDays(10));
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.COMPLETED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenTrainingIsAlreadyFinished() {
        given(clock.now()).willReturn(TRAINING_ENDS_AT);
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.CONFIRMED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    @Test
    void shouldNotCancelWhenTrainingIsAlreadyStarted() {
        given(clock.now()).willReturn(TRAINING_STARTS_AT);
        given(trainingAvailabilityRepository.findById(any())).willReturn(mock(TrainingAvailability.class));
        Reservation reservation = mock(Reservation.class);
        given(reservation.asReservationDto()).willReturn(new ReservationDto(ReservationStatus.CONFIRMED, PARTICIPANT_ID, TRAINING_STARTS_AT, TRAINING_ENDS_AT));
        given(reservationRepository.findById(any())).willReturn(reservation);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.cancel(command()));

        assertThat(actual).hasMessage("Cancellation not allowed.");
        then(reservation).should(never()).cancel();
        then(trainingAvailabilityRepository).should(never()).save(any());
        then(reservationRepository).should(never()).save(any());
    }

    private CancelReservationCommand command() {
        return new CancelReservationCommand(TRAINING_AVAILABILITY_ID, RESERVATION_ID);
    }
}
