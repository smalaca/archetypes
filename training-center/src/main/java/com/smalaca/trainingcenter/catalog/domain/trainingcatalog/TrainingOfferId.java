package com.smalaca.trainingcenter.catalog.domain.trainingcatalog;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductTypeIdentifier
record TrainingOfferId(UUID id) {
}
