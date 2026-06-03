package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainerFactoryTest {
    private final TrainerId trainerId = new TrainerId(UUID.randomUUID());
    private final UserId userId = new UserId(UUID.randomUUID());
    private final TrainerNumber trainerNumber = new TrainerNumber(UUID.randomUUID());

    private final SeniorityService seniorityService = mock(SeniorityService.class);

    private TrainerFactory factory;

    @BeforeEach
    void setUp() {
        factory = new TrainerFactory(new TrainerExperienceSeniorityConstraint(seniorityService));
    }

    @Test
    void shouldCreateTrainerWhenConstraintIsSatisfied() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(true);

        Trainer trainer = factory.create(trainerId, userId, trainerNumber);

        assertThat(trainer).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(false);

        assertThatThrownBy(() -> factory.create(trainerId, userId, trainerNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Trainer constraints not satisfied");
    }
}
