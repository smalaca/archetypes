package com.smalaca.trainingcenter.catalog.query.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferId;

import java.util.List;
import java.util.Optional;

@ArchetypeProduct.Catalog
public interface LearningOfferReadModelRepository {
    List<LearningOfferView> findAll();

    Optional<LearningOfferView> findById(LearningOfferId id);

    List<LearningOfferView> findByTitle(String title);

    List<LearningOfferView> findByCategory(String category);
}
