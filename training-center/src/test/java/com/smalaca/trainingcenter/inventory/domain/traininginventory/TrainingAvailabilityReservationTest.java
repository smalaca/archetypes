package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.trainingcenter.inventory.domain.reservation.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainingAvailabilityReservationTest {
    private static final TrainingSessionId TRAINING_SESSION_ID = new TrainingSessionId(UUID.randomUUID());
    private static final LocalDateTime REQUESTED_AT = LocalDateTime.of(2026, 4, 1, 10, 0);

    @Test
    void shouldCreateReservationSuccessfullyWhenSeatsAreAvailable() {
        TrainingAvailability availability = trainingAvailability(20, 10);
        ParticipantId participantId = participantId();
        ReservationRequest request = new ReservationRequest(TRAINING_SESSION_ID, participantId, REQUESTED_AT);

        Reservation actual = availability.reserve(request);

        assertThat(actual).extracting("trainingSessionId").isEqualTo(TRAINING_SESSION_ID);
        assertThat(actual).extracting("participantId").isEqualTo(participantId);
        assertThat(actual).extracting("reservationId").isNotNull();
        assertThat(actual).extracting("reservedAt").isEqualTo(REQUESTED_AT);
        assertThat(actual).extracting("status").isEqualTo(ReservationStatus.CONFIRMED);
    }

    @Test
    void shouldUpdateReservedSeatsAfterReservation() {
        TrainingAvailability availability = trainingAvailability(20, 10);
        ReservationRequest request = new ReservationRequest(TRAINING_SESSION_ID, participantId(), REQUESTED_AT);

        availability.reserve(request);

        assertReservedSeats(availability, 11);
    }

    @Test
    void shouldReserveSuccessfullyWhenOneSeatRemains() {
        TrainingAvailability availability = trainingAvailability(20, 19);
        ReservationRequest request = new ReservationRequest(TRAINING_SESSION_ID, participantId(), REQUESTED_AT);

        availability.reserve(request);

        assertReservedSeats(availability, 20);
    }

    @Test
    void shouldRejectReservationWhenCapacityIsReached() {
        TrainingAvailability availability = trainingAvailability(20, 20);
        ReservationRequest request = new ReservationRequest(TRAINING_SESSION_ID, participantId(), REQUESTED_AT);

        TrainingAvailabilityException actual = assertThrows(TrainingAvailabilityException.class, () -> availability.reserve(request));

        assertThat(actual.getMessage()).isEqualTo("No seats available.");
        assertReservedSeats(availability, 20);
    }

    private void assertReservedSeats(TrainingAvailability availability, int expected) {
        assertThat(availability).extracting("reservedSeats").extracting("value").isEqualTo(expected);
    }

    private ParticipantId participantId() {
        return new ParticipantId(UUID.randomUUID());
    }

    private TrainingAvailability trainingAvailability(int capacity, int reserved) {
        return new TrainingAvailability(
                new TrainingAvailabilityId(UUID.randomUUID()),
                TRAINING_SESSION_ID,
                new Capacity(capacity),
                new ReservedSeats(reserved));
    }
}
