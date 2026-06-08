package com.smalaca.trainingcenter.catalog.domain.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.HashSet;
import java.util.Set;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.CatalogEntry
public class LearningOffer {
    private final LearningOfferId learningOfferId;
    private final String title;
    private final String description;
    private final Set<TrainingOfferId> trainingOfferIds = new HashSet<>();

    public LearningOffer(LearningOfferId learningOfferId, String title, String description) {
        this.learningOfferId = learningOfferId;
        this.title = title;
        this.description = description;
    }

    public void add(Set<TrainingOfferId> trainingOfferId) {
        trainingOfferIds.addAll(trainingOfferId);
    }
}
