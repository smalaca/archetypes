package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import java.util.UUID;

public record CreateTrainingAvailabilityCommand(UUID trainingSessionId, int capacity, int reservedSeats) {
}
