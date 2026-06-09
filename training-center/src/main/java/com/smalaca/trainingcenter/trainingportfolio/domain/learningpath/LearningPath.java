package com.smalaca.trainingcenter.trainingportfolio.domain.learningpath;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.TrainingOfferId;

import java.util.HashSet;
import java.util.Set;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.ProductType
@ArchetypeProduct.PackageType
public class LearningPath {
    private final LearningPathId learningPathId;
    private final String name;
    private final Set<TrainingOfferId> trainingOfferIds = new HashSet<>();

    public LearningPath(LearningPathId learningPathId, String name) {
        this.learningPathId = learningPathId;
        this.name = name;
    }

    public void add(TrainingOfferId trainingOfferId) {
        trainingOfferIds.add(trainingOfferId);
    }
}
