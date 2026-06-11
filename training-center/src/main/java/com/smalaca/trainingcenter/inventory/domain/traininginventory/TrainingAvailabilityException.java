package com.smalaca.trainingcenter.inventory.domain.traininginventory;

class TrainingAvailabilityException extends RuntimeException {
    private TrainingAvailabilityException(String message) {
        super(message);
    }

    static TrainingAvailabilityException noSeatsAvailable() {
        return new TrainingAvailabilityException("No seats available.");
    }
}
