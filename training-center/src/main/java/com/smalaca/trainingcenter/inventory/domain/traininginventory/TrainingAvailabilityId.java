package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
record TrainingAvailabilityId(UUID id) {
}
