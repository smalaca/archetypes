package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import java.util.List;

public class TrainerFactory {
    private final TrainerExperienceSeniorityConstraint constraint;
    private final ExpertiseService expertiseService;
    private final WorkloadService workloadService;
    private final CertificationService certificationService;

    private TrainerFactory(
            TrainerExperienceSeniorityConstraint constraint, ExpertiseService expertiseService,
            WorkloadService workloadService, CertificationService certificationService) {
        this.constraint = constraint;
        this.expertiseService = expertiseService;
        this.workloadService = workloadService;
        this.certificationService = certificationService;
    }

    public static TrainerFactory trainerFactory(
            SeniorityService seniorityService, ExpertiseService expertiseService,
            WorkloadService workloadService, CertificationService certificationService) {
        return new TrainerFactory(
                new TrainerExperienceSeniorityConstraint(seniorityService),
                expertiseService, workloadService, certificationService);
    }

    public Trainer create(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber) {
        if (constraint.isSatisfiedBy(userId)) {
            TrainingAcceptancePolicy policy = trainingAcceptancePolicy();
            return new Trainer(trainerId, userId, trainerNumber, policy);
        }

        throw new RuntimeException("Trainer constraints not satisfied");
    }

    private TrainingAcceptancePolicy trainingAcceptancePolicy() {
        return new TrainingAcceptancePolicy(List.of(
                new TrainerExpertiseRule(expertiseService),
                new TrainerWorkloadRule(workloadService),
                new TrainerCertificationRule(certificationService)
        ));
    }
}
