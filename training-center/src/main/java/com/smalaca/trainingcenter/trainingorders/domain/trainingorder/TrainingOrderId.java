package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record TrainingOrderId(UUID id) {
    @DomainDrivenDesign.Factory
    static TrainingOrderId trainingOrderId() {
        return new TrainingOrderId(UUID.randomUUID());
    }
}
