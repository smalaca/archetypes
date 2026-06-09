package com.smalaca.trainingcenter.trainingdelivery.domain.learningpathedition;

import com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.TrainingSessionId;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.SET;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LearningPathEditionTest {
    @Test
    void shouldCreateLearningPathEditionWithTrainingSessions() {
        LearningPathEditionId editionId = new LearningPathEditionId(UUID.randomUUID());
        LearningPathId learningPathId = new LearningPathId(UUID.randomUUID());
        LearningPathEdition actual = new LearningPathEdition(editionId, learningPathId);
        TrainingSessionId dddKrakow = new TrainingSessionId(UUID.randomUUID());
        TrainingSessionId eventStormingKrakow = new TrainingSessionId(UUID.randomUUID());

        actual.add(dddKrakow, Set.of(actual));
        actual.add(eventStormingKrakow, Set.of(actual));

        assertThat(actual).extracting("learningPathEditionId").isEqualTo(editionId);
        assertThat(actual).extracting("learningPathId").isEqualTo(learningPathId);
        assertThat(actual).extracting("trainingSessionIds").asInstanceOf(SET)
                .containsExactlyInAnyOrder(dddKrakow, eventStormingKrakow);
    }

    @Test
    void shouldThrowExceptionWhenTrainingSessionAlreadyBelongsToAnotherEdition() {
        TrainingSessionId dddKrakow = new TrainingSessionId(UUID.randomUUID());
        LearningPathEdition edition = learningPathEdition();
        edition.add(dddKrakow, Set.of(edition));
        LearningPathEdition actual = learningPathEdition();

        LearningPathEditionException thrown = assertThrows(LearningPathEditionException.class, () -> actual.add(dddKrakow, Set.of(edition, actual)));

        assertThat(thrown).hasMessageContaining("already belongs to another Learning Path Edition");
    }

    @Test
    void shouldNotAddTrainingSessionThatAlreadyBelongsToAnotherEdition() {
        TrainingSessionId dddKrakow = new TrainingSessionId(UUID.randomUUID());
        LearningPathEdition edition = learningPathEdition();
        edition.add(dddKrakow, Set.of(edition));
        LearningPathEdition actual = learningPathEdition();

        assertThrows(LearningPathEditionException.class, () -> actual.add(dddKrakow, Set.of(edition, actual)));

        assertThat(actual).extracting("trainingSessionIds").asInstanceOf(SET).isEmpty();
    }

    private LearningPathEdition learningPathEdition() {
        return new LearningPathEdition(new LearningPathEditionId(UUID.randomUUID()), new LearningPathId(UUID.randomUUID()));
    }
}
