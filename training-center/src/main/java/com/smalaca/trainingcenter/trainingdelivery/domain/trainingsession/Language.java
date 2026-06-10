package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductFeatureInstance
public record Language(String value) {
}
