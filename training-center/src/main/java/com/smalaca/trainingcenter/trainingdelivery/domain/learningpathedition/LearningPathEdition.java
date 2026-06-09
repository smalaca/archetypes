package com.smalaca.trainingcenter.trainingdelivery.domain.learningpathedition;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.TrainingSessionId;

import java.util.HashSet;
import java.util.Set;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.PackageInstance
@ArchetypeProduct.ProductInstance
public class LearningPathEdition {
    private final LearningPathEditionId learningPathEditionId;
    private final LearningPathId learningPathId;
    private final Set<TrainingSessionId> trainingSessionIds = new HashSet<>();

    public LearningPathEdition(LearningPathEditionId learningPathEditionId, LearningPathId learningPathId) {
        this.learningPathEditionId = learningPathEditionId;
        this.learningPathId = learningPathId;
    }

    public void add(TrainingSessionId trainingSessionId, Set<LearningPathEdition> allEditions) {
        if (alreadyBelongsToOtherEdition(trainingSessionId, allEditions)) {
            throw new LearningPathEditionException("Training Session " + trainingSessionId + " already belongs to another Learning Path Edition.");
        }

        trainingSessionIds.add(trainingSessionId);
    }

    private boolean alreadyBelongsToOtherEdition(TrainingSessionId trainingSessionId, Set<LearningPathEdition> allEditions) {
        return allEditions.stream()
                .filter(edition -> !edition.equals(this))
                .anyMatch(edition -> edition.contains(trainingSessionId));
    }

    private boolean contains(TrainingSessionId trainingSessionId) {
        return trainingSessionIds.contains(trainingSessionId);
    }
}
