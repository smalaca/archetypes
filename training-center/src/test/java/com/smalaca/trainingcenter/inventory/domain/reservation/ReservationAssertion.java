package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.trainingcenter.inventory.domain.participantid.ParticipantId;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingSessionId;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

public class ReservationAssertion extends AbstractAssert<ReservationAssertion, Reservation> {
    private ReservationAssertion(Reservation actual) {
        super(actual, ReservationAssertion.class);
    }

    public static ReservationAssertion assertThat(Reservation actual) {
        return new ReservationAssertion(actual);
    }

    public ReservationAssertion hasTrainingSessionIdEqualTo(TrainingSessionId expected) {
        Assertions.assertThat(actual).extracting("trainingSessionId").isEqualTo(expected);
        return this;
    }

    public ReservationAssertion hasParticipantIdEqualTo(ParticipantId expected) {
        Assertions.assertThat(actual).extracting("participantId").isEqualTo(expected);
        return this;
    }

    public ReservationAssertion hasReservationId() {
        Assertions.assertThat(actual).extracting("reservationId").isNotNull();
        return this;
    }

    public ReservationAssertion hasReservedAtEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).extracting("reservedAt").isEqualTo(expected);
        return this;
    }

    public ReservationAssertion isConfirmed() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(ReservationStatus.CONFIRMED);
        return this;
    }

    public ReservationAssertion isRejected() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(ReservationStatus.REJECTED);
        return this;
    }

    public ReservationAssertion isCompleted() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(ReservationStatus.COMPLETED);
        return this;
    }

    public ReservationAssertion isCancelled() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(ReservationStatus.CANCELLED);
        return this;
    }

    public ReservationAssertion hasTrainingStartsAtEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).extracting("trainingStartsAt").isEqualTo(expected);
        return this;
    }

    public ReservationAssertion hasTrainingEndsAtEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).extracting("trainingEndsAt").isEqualTo(expected);
        return this;
    }

    public ReservationAssertion hasNoEnrollmentApproval() {
        Assertions.assertThat(actual).extracting("enrollmentApproval").isNull();
        return this;
    }

    public ReservationAssertion hasEnrollmentApproval(TrainingCoordinatorId expectedCoordinatorId, LocalDateTime expectedApprovedAt, String expectedJustification) {
        Assertions.assertThat(actual).extracting("enrollmentApproval").isNotNull();
        Assertions.assertThat(actual).extracting("enrollmentApproval").extracting("approvalSignature").extracting("trainingCoordinatorId").isEqualTo(expectedCoordinatorId);
        Assertions.assertThat(actual).extracting("enrollmentApproval").extracting("approvalSignature").extracting("approvedAt").isEqualTo(expectedApprovedAt);
        Assertions.assertThat(actual).extracting("enrollmentApproval").extracting("justification").isEqualTo(expectedJustification);
        return this;
    }
}
