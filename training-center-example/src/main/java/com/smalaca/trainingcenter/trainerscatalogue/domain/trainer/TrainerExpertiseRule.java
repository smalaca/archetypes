package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;

@ArchetypeRule.Rule
class TrainerExpertiseRule implements TrainingRule {
    private final ExpertiseService expertiseService;

    TrainerExpertiseRule(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

    @Override
    public boolean isSatisfiedBy(TrainingContext context) {
        return expertiseService.hasExpertiseIn(context.trainerUserId(), context.topicId());
    }
}
