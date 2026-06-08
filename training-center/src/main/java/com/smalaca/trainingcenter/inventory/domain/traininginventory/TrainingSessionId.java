package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductInstanceIdentifier
record TrainingSessionId(UUID id) {
}
