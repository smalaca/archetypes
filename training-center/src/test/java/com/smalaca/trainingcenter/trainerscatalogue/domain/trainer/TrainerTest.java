package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainerTest {
    private final ExpertiseService expertiseService = mock(ExpertiseService.class);
    private final WorkloadService workloadService = mock(WorkloadService.class);
    private final CertificationService certificationService = mock(CertificationService.class);

    private final TrainerId trainerId = new TrainerId(UUID.randomUUID());
    private final UserId userId = new UserId(UUID.randomUUID());
    private final TrainerNumber trainerNumber = new TrainerNumber(UUID.randomUUID());
    private Trainer trainer;

    private final UUID trainingId = UUID.randomUUID();
    private final UUID topicId = UUID.randomUUID();
    private final UUID levelId = UUID.randomUUID();
    private final TrainingContext context = new TrainingContext(trainingId, topicId, levelId, userId);

    @BeforeEach
    void createTrainer() {
        SeniorityService seniorityService = mock(SeniorityService.class);
        given(seniorityService.hasEnoughExperience(userId)).willReturn(true);
        TrainerFactory factory = TrainerFactory.trainerFactory(seniorityService, expertiseService, workloadService, certificationService);

        trainer = factory.create(trainerId, userId, trainerNumber);
    }

    @Test
    void shouldAcceptTrainingWhenAllRulesAreSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().containsExactly(trainingId);
    }

    @Test
    void shouldNotAcceptTrainingWhenExpertiseRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(false);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().isEmpty();
        assertThat(trainer).extracting("trainingsForManualIntervention").asList().isEmpty();
    }

    @Test
    void shouldRequireManualInterventionWhenWorkloadRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(false);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().isEmpty();
        assertThat(trainer).extracting("trainingsForManualIntervention").asList().containsExactly(trainingId);
    }

    @Test
    void shouldRequireManualInterventionWhenCertificationRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(false);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().isEmpty();
        assertThat(trainer).extracting("trainingsForManualIntervention").asList().containsExactly(trainingId);
    }

    @Test
    void shouldAcceptAnotherTrainingWhenOneWasAlreadyAccepted() {
        UUID acceptedTrainingId = givenTrainerWithAcceptedTraining();

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().containsExactlyInAnyOrder(acceptedTrainingId, trainingId);
    }

    @Test
    void shouldNotAcceptAnotherTrainingWhenRulesAreNotSatisfiedAndOneWasAlreadyAccepted() {
        UUID acceptedTrainingId = givenTrainerWithAcceptedTraining();
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(false);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("acceptedTrainings").asList().containsExactly(acceptedTrainingId);
    }

    @Test
    void shouldRequireAnotherManualInterventionWhenOneWasAlreadyRequired() {
        UUID manualTrainingId = givenTrainerWithTrainingForManualIntervention();

        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(false);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("trainingsForManualIntervention").asList().containsExactlyInAnyOrder(manualTrainingId, trainingId);
    }

    @Test
    void shouldNotRequireAnotherManualInterventionWhenExpertiseRuleIsNotSatisfiedAndOneWasAlreadyRequired() {
        UUID manualTrainingId = givenTrainerWithTrainingForManualIntervention();
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(false);

        trainer.acceptTraining(context);

        assertThat(trainer).extracting("trainingsForManualIntervention").asList().containsExactly(manualTrainingId);
    }

    private UUID givenTrainerWithTrainingForManualIntervention() {
        UUID existingTrainingId = UUID.randomUUID();
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(false);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);
        trainer.acceptTraining(new TrainingContext(existingTrainingId, topicId, levelId, userId));

        return existingTrainingId;
    }

    private UUID givenTrainerWithAcceptedTraining() {
        UUID existingTrainingId = UUID.randomUUID();
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);
        trainer.acceptTraining(new TrainingContext(existingTrainingId, topicId, levelId, userId));

        return existingTrainingId;
    }
}
