package com.smalaca.trainingcenter.catalog.query.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeCatalog;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferId;

import java.util.List;
import java.util.Optional;

@ArchetypeCatalog.Catalog
public interface LearningOfferReadModelRepository {
    List<LearningOfferView> findAll();

    Optional<LearningOfferView> findById(LearningOfferId id);

    List<LearningOfferView> findByTitle(String title);

    List<LearningOfferView> findByCategory(String category);
}
