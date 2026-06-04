package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;

@ArchetypeRule.Rule
class TrainerWorkloadRule {
    private final WorkloadService workloadService;

    TrainerWorkloadRule(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    boolean isSatisfiedBy(TrainingContext context) {
        return workloadService.hasCapacity(context.trainerUserId());
    }
}
