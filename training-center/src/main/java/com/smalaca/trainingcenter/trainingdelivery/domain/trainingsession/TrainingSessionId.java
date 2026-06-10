package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductInstanceIdentifier
public record TrainingSessionId(UUID id) {
}
