package com.smalaca.trainingcenter.trainingportfolio.domain.learningpath;

import com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.TrainingOfferId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.SET;

class LearningPathTest {
    @Test
    void shouldCreateLearningPathWithTrainingOffers() {
        LearningPathId learningPathId = new LearningPathId(UUID.randomUUID());
        LearningPath learningPath = new LearningPath(learningPathId, "Architecture Academy");
        TrainingOfferId dddTraining = new TrainingOfferId(UUID.randomUUID());
        TrainingOfferId eventStorming = new TrainingOfferId(UUID.randomUUID());
        TrainingOfferId microservices = new TrainingOfferId(UUID.randomUUID());

        learningPath.add(dddTraining);
        learningPath.add(eventStorming);
        learningPath.add(microservices);

        assertThat(learningPath).extracting("learningPathId").isEqualTo(learningPathId);
        assertThat(learningPath).extracting("name").isEqualTo("Architecture Academy");
        assertThat(learningPath).extracting("trainingOfferIds").asInstanceOf(SET)
                .containsExactlyInAnyOrder(dddTraining, eventStorming, microservices);
    }

}
