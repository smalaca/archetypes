package com.smalaca.trainingcenter.productcatalog.domain.trainingoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.List;

@DomainDrivenDesign.ValueObject
@ArchetypeProduct.ProductFeatureType
record TrainingFeature(String name, String description, List<String> possibleValues) {

}
