package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.inventory.domain.clock.Clock;
import com.smalaca.trainingcenter.inventory.domain.reservation.Reservation;
import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationDto;

@DomainDrivenDesign.DomainService
public class ReservationCancellationService {
    private final TrainingCancellationPolicy cancellationPolicy;
    private final Clock clock;

    private ReservationCancellationService(TrainingCancellationPolicy cancellationPolicy, Clock clock) {
        this.cancellationPolicy = cancellationPolicy;
        this.clock = clock;
    }

    public static ReservationCancellationService create(Clock clock) {
        return new ReservationCancellationService(TrainingCancellationPolicy.create(), clock);
    }

    public void cancel(Reservation reservation, TrainingAvailability availability) {
        ReservationDto reservationDto = reservation.asReservationDto();
        TrainingCancellationContext context = TrainingCancellationContext.create(reservationDto, clock.now());

        if (!cancellationPolicy.allows(context)) {
            throw new RuntimeException("Cancellation not allowed.");
        }

        reservation.cancel();
        availability.releaseSeat(context.participantId());
    }
}
