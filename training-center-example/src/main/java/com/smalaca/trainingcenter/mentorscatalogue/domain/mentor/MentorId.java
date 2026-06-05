package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyRoleIdentifier
record MentorId(UUID id) {
}
