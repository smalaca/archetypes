package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
@ArchetypeAvailability.CancellationPolicy
interface TrainingCancellationRule {
    boolean isSatisfiedBy(TrainingCancellationContext context);
}
