package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.math.BigDecimal;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.Price
record TrainingSessionPrice(BigDecimal amount, String currency) {
}
