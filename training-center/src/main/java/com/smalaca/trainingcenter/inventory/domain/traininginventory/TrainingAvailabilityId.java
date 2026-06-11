package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record TrainingAvailabilityId(UUID id) {
    @DomainDrivenDesign.Factory
    public static TrainingAvailabilityId trainingAvailabilityId() {
        return new TrainingAvailabilityId(UUID.randomUUID());
    }
}
