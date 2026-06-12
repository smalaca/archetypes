package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeAvailability;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
@ArchetypeAvailability.AvailabilityPolicy
interface TrainingEnrollmentRule {
    boolean isSatisfiedBy(TrainingEnrollmentContext context);
}
