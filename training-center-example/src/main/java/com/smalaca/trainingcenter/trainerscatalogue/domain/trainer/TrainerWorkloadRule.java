package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;

@ArchetypeRule.Rule
class TrainerWorkloadRule implements TrainingRule {
    private final WorkloadService workloadService;

    TrainerWorkloadRule(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    @Override
    public boolean isSatisfiedBy(TrainingContext context) {
        return workloadService.hasCapacity(context.trainerUserId());
    }
}
