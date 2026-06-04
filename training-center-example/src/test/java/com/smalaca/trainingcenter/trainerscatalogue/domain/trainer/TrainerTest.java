package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainerTest {
    private final ExpertiseService expertiseService = mock(ExpertiseService.class);
    private final WorkloadService workloadService = mock(WorkloadService.class);
    private final CertificationService certificationService = mock(CertificationService.class);

    private final TrainerExpertiseRule expertiseRule = new TrainerExpertiseRule(expertiseService);
    private final TrainerWorkloadRule workloadRule = new TrainerWorkloadRule(workloadService);
    private final TrainerCertificationRule certificationRule = new TrainerCertificationRule(certificationService);

    private final TrainingAcceptanceRuleSet ruleSet = new TrainingAcceptanceRuleSet(expertiseRule, workloadRule, certificationRule);
    
    private final TrainerId trainerId = new TrainerId(UUID.randomUUID());
    private final UserId userId = new UserId(UUID.randomUUID());
    private final TrainerNumber trainerNumber = new TrainerNumber(UUID.randomUUID());
    private final Trainer trainer = new Trainer(trainerId, userId, trainerNumber, ruleSet);

    private final UUID topicId = UUID.randomUUID();
    private final UUID levelId = UUID.randomUUID();
    private final TrainingContext context = new TrainingContext(topicId, levelId, userId);

    @Test
    void shouldAcceptTrainingWhenAllRulesAreSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        boolean actual = trainer.acceptTraining(context);

        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotAcceptTrainingWhenExpertiseRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(false);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        boolean actual = trainer.acceptTraining(context);

        assertThat(actual).isFalse();
    }

    @Test
    void shouldNotAcceptTrainingWhenWorkloadRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(false);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(true);

        boolean actual = trainer.acceptTraining(context);

        assertThat(actual).isFalse();
    }

    @Test
    void shouldNotAcceptTrainingWhenCertificationRuleIsNotSatisfied() {
        given(expertiseService.hasExpertiseIn(userId, topicId)).willReturn(true);
        given(workloadService.hasCapacity(userId)).willReturn(true);
        given(certificationService.isCertifiedFor(userId, topicId, levelId)).willReturn(false);

        boolean actual = trainer.acceptTraining(context);

        assertThat(actual).isFalse();
    }
}
