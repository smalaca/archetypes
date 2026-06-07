package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRelationshipConstraint
class RoleCompatibilityConstraint implements MentoringCondition {
    private static final int MAX_MENTEES = 5;

    @Override
    public boolean isSatisfiedBy(MentoringContext context) {
        return !context.menteeIds().isEmpty() && context.menteeIds().size() <= MAX_MENTEES;
    }
}
