package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface MentorConstraint {
    boolean isSatisfiedBy(UserId userId);
}
