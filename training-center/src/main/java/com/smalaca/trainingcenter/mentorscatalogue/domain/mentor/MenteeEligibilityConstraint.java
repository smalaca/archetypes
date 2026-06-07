package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRelationshipConstraint
class MenteeEligibilityConstraint implements MentoringCondition {
    private final MenteeService menteeService;

    MenteeEligibilityConstraint(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @Override
    public boolean isSatisfiedBy(MentoringContext context) {
        return context.menteeIds().stream().allMatch(menteeService::canBeMentored);
    }
}
