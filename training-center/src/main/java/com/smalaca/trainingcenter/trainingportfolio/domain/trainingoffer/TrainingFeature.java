package com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.List;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductFeatureType
record TrainingFeature(String name, String description, List<String> possibleValues) {

    public boolean hasSameNameAs(TrainingFeature trainingFeature) {
        return name.equals(trainingFeature.name);
    }
}
