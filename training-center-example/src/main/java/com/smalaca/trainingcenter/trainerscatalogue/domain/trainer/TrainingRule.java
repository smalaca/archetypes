package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;

@ArchetypeRule.Rule
interface TrainingRule {
    boolean isSatisfiedBy(TrainingContext context);
}
