package com.smalaca.trainingcenter.trainingdelivery.domain.learningpathedition;

import com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.TrainingSessionId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.SET;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LearningPathEditionTest {
    private static final LearningPathEditionId EDITION_ID = new LearningPathEditionId(UUID.randomUUID());
    private static final LearningPathId LEARNING_PATH_ID = new LearningPathId(UUID.randomUUID());
    private static final TrainingSessionId TRAINING_SESSION_ID = new TrainingSessionId(UUID.randomUUID());

    private final LearningPathEditionRepository repository = mock(LearningPathEditionRepository.class);

    @Test
    void shouldCreateLearningPathEdition() {
        LearningPathEdition actual = new LearningPathEdition(EDITION_ID, LEARNING_PATH_ID);

        assertThat(actual).extracting("learningPathEditionId").isEqualTo(EDITION_ID);
        assertThat(actual).extracting("learningPathId").isEqualTo(LEARNING_PATH_ID);
    }

    @Test
    void shouldAddTrainingSessionsToLearningPathEdition() {
        LearningPathEdition actual = learningPathEdition();
        TrainingSessionId dddKrakow = new TrainingSessionId(UUID.randomUUID());
        TrainingSessionId eventStormingKrakow = new TrainingSessionId(UUID.randomUUID());
        given(repository.existsAnyWith(dddKrakow)).willReturn(false);
        given(repository.existsAnyWith(eventStormingKrakow)).willReturn(false);

        actual.add(dddKrakow, repository);
        actual.add(eventStormingKrakow, repository);

        assertThat(actual).extracting("trainingSessionIds").asInstanceOf(SET)
                .containsExactlyInAnyOrder(dddKrakow, eventStormingKrakow);
    }

    @Test
    void shouldThrowExceptionWhenTrainingSessionAlreadyBelongsToAnotherEdition() {
        LearningPathEdition actual = learningPathEdition();
        given(repository.existsAnyWith(TRAINING_SESSION_ID)).willReturn(true);

        LearningPathEditionException thrown = assertThrows(LearningPathEditionException.class, () -> actual.add(TRAINING_SESSION_ID, repository));

        assertThat(thrown).hasMessageContaining("already belongs to another Learning Path Edition");
    }

    @Test
    void shouldNotAddTrainingSessionThatAlreadyBelongsToAnotherEdition() {
        LearningPathEdition actual = learningPathEdition();
        given(repository.existsAnyWith(TRAINING_SESSION_ID)).willReturn(true);

        assertThrows(LearningPathEditionException.class, () -> actual.add(TRAINING_SESSION_ID, repository));

        assertThat(actual).extracting("trainingSessionIds").asInstanceOf(SET).isEmpty();
    }

    private LearningPathEdition learningPathEdition() {
        return new LearningPathEdition(EDITION_ID, LEARNING_PATH_ID);
    }
}
