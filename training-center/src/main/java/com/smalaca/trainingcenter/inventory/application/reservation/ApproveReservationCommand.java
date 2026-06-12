package com.smalaca.trainingcenter.inventory.application.reservation;

import java.util.UUID;

public record ApproveReservationCommand(UUID reservationId, UUID trainingCoordinatorId, String justification) {
}
