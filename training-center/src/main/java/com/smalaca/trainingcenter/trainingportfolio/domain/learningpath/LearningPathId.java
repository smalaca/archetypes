package com.smalaca.trainingcenter.trainingportfolio.domain.learningpath;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductTypeIdentifier
public record LearningPathId(UUID id) {
}
