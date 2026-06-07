package com.smalaca.trainingcenter.productcatalog.domain.trainingoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.ProductType
public class TrainingOffer {
    private final TrainingOfferId trainingOfferId;
    private final TrainingOfferName name;
    private final List<TrainingPrice> prices = new ArrayList<>();
    private final List<TrainingFeature> features = new ArrayList<>();

    public TrainingOffer(TrainingOfferId trainingOfferId, TrainingOfferName name) {
        this.trainingOfferId = trainingOfferId;
        this.name = name;
    }

    public void add(TrainingPrice trainingPrice) {
        prices.add(trainingPrice);
    }

    public void add(TrainingFeature trainingFeature) {
        features.add(trainingFeature);
    }
}
