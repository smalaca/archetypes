package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
class ExperienceSeniorityConstraint implements MentorConstraint {
    @Override
    public boolean isSatisfiedBy(UserId userId) {
        return true;
    }
}
