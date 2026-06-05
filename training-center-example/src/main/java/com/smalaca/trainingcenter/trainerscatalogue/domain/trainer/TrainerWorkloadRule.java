package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Policy
@ArchetypeRule.Rule
class TrainerWorkloadRule implements TrainingRule {
    private final WorkloadService workloadService;

    TrainerWorkloadRule(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    @Override
    public TrainingAcceptanceResult isSatisfiedBy(TrainingContext context) {
        if (workloadService.hasCapacity(context.trainerUserId())) {
            return TrainingAcceptanceResult.accepted();
        }

        return TrainingAcceptanceResult.manualIntervention("Trainer workload exceeded.");
    }
}
