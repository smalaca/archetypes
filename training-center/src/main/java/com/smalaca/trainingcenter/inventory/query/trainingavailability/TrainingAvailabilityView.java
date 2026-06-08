package com.smalaca.trainingcenter.inventory.query.trainingavailability;

import java.util.UUID;

public record TrainingAvailabilityView(UUID trainingAvailabilityId, UUID trainingSessionId, int capacity, int reservedSeats, int availableSeats) {
}
