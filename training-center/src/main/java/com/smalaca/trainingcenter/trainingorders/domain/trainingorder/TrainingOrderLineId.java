package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
record TrainingOrderLineId(UUID id) {
    static TrainingOrderLineId trainingOrderLineId() {
        return new TrainingOrderLineId(UUID.randomUUID());
    }
}
