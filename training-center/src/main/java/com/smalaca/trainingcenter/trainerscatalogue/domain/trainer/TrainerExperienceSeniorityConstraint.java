package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRoleConstraint
class TrainerExperienceSeniorityConstraint {
    private final SeniorityService seniorityService;

    TrainerExperienceSeniorityConstraint(SeniorityService seniorityService) {
        this.seniorityService = seniorityService;
    }

    boolean isSatisfiedBy(UserId userId) {
        return seniorityService.hasEnoughExperience(userId);
    }
}
