package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record UserId(UUID id) {
}
