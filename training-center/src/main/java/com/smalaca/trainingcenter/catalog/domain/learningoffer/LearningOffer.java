package com.smalaca.trainingcenter.catalog.domain.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.Entity
@ArchetypeProduct.CatalogEntry
public class LearningOffer {
    private final LearningOfferId learningOfferId;
    private final String title;
    private final String description;
    private final List<TrainingOfferId> trainingOfferIds = new ArrayList<>();

    public LearningOffer(LearningOfferId learningOfferId, String title, String description) {
        this.learningOfferId = learningOfferId;
        this.title = title;
        this.description = description;
    }

    public void add(TrainingOfferId trainingOfferId) {
        trainingOfferIds.add(trainingOfferId);
    }
}
