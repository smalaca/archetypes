package com.smalaca.trainingcenter.trainingdelivery.domain.learningpathedition;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductInstanceIdentifier
record LearningPathEditionId(UUID id) {
}
