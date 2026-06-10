package com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductFeatureType
public enum DeliveryMode {
    ONLINE,
    ONSITE,
    HYBRID
}
