package com.smalaca.trainingcenter.productcatalog.domain.trainingoffer;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
enum PriceType {
    STANDARD,
    EARLY_BIRD,
    CORPORATE,
    PARTNER,
    STUDENT
}
