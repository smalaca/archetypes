package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductTypeIdentifier
public record SellableItemId(UUID id) {
}