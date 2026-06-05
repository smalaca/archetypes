package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface MentorConstraint {
    boolean isSatisfiedBy(UserId userId);
}
