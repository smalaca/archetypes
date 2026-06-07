package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Policy
@ArchetypeRule.Rule
class TrainerExpertiseRule implements TrainingRule {
    private final ExpertiseService expertiseService;

    TrainerExpertiseRule(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

    @Override
    public TrainingAcceptanceResult isSatisfiedBy(TrainingContext context) {
        if (expertiseService.hasExpertiseIn(context.trainerUserId(), context.topicId())) {
            return TrainingAcceptanceResult.accepted();
        }

        return TrainingAcceptanceResult.rejected("Trainer does not have expertise in the topic.");
    }
}
