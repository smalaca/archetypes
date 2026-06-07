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
        if (hasSamePriceTypeAs(trainingPrice)) {
            throw new RuntimeException("Price type already exists: " + trainingPrice.type());
        }
        prices.add(trainingPrice);
    }

    private boolean hasSamePriceTypeAs(TrainingPrice trainingPrice) {
        return prices.stream().anyMatch(price -> price.hasSameTypeAs(trainingPrice));
    }

    public void add(TrainingFeature trainingFeature) {
        if (hasSameFeatureNameAs(trainingFeature)) {
            throw new RuntimeException("Feature name already exists: " + trainingFeature.name());
        }
        features.add(trainingFeature);
    }

    private boolean hasSameFeatureNameAs(TrainingFeature trainingFeature) {
        return features.stream().anyMatch(feature -> feature.hasSameNameAs(trainingFeature));
    }
}
