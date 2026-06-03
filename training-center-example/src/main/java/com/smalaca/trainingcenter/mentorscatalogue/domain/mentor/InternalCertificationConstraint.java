package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class InternalCertificationConstraint implements MentorConstraint {
    @Override
    public boolean isSatisfiedBy(UserId userId) {
        return true;
    }
}
