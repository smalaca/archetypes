package com.smalaca.trainingcenter.productcatalog.domain.trainingoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.math.BigDecimal;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.Price
record TrainingPrice(PriceType type, BigDecimal amount, String currency) {
}
