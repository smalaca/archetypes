package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainerFactoryTest {
    private static final TrainerId DUMMY_TRAINER_ID = new TrainerId(UUID.randomUUID());
    private static final UserId DUMMY_USER_ID = new UserId(UUID.randomUUID());
    private static final TrainerNumber DUMMY_TRAINER_NUMBER = new TrainerNumber(UUID.randomUUID());

    private final SeniorityService seniorityService = mock(SeniorityService.class);
    private final TrainerFactory factory = TrainerFactory.trainerFactory(seniorityService);

    @Test
    void shouldCreateTrainerWhenConstraintIsSatisfied() {
        given(seniorityService.hasEnoughExperience(DUMMY_USER_ID)).willReturn(true);

        Trainer trainer = factory.create(DUMMY_TRAINER_ID, DUMMY_USER_ID, DUMMY_TRAINER_NUMBER);

        assertThat(trainer).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(DUMMY_USER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_TRAINER_ID, DUMMY_USER_ID, DUMMY_TRAINER_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Trainer constraints not satisfied");
    }
}
