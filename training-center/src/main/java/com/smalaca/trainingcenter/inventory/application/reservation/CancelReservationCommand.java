package com.smalaca.trainingcenter.inventory.application.reservation;

import java.util.UUID;

public record CancelReservationCommand(UUID trainingAvailabilityId, UUID reservationId) {
}
