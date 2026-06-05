package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRoleConstraint
interface MentorConstraint {
    boolean isSatisfiedBy(UserId userId);
}
