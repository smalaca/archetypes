package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Policy
@ArchetypeRule.Rule
interface TrainingRule {
    TrainingAcceptanceResult isSatisfiedBy(TrainingContext context);
}
