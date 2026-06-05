package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;

@ArchetypeRule.Rule
interface TrainingRule {
    boolean isSatisfiedBy(TrainingContext context);
}
