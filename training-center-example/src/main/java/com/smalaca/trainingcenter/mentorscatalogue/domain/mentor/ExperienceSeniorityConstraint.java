package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class ExperienceSeniorityConstraint implements MentorConstraint {
    private final SeniorityService seniorityService;

    ExperienceSeniorityConstraint(SeniorityService seniorityService) {
        this.seniorityService = seniorityService;
    }

    @Override
    public boolean isSatisfiedBy(UserId userId) {
        return seniorityService.hasEnoughExperience(userId);
    }
}
