package com.smalaca.trainingcenter.catalog.domain.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;

@DomainDrivenDesign.Repository
@PortsAndAdaptersArchitecture.DrivenPort
@ArchetypeProduct.Catalog
public interface LearningOfferRepository {
    LearningOffer findById(LearningOfferId id);

    void save(LearningOffer learningOffer);

    void delete(LearningOfferId id);
}
