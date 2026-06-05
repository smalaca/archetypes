package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record UserId(UUID id) {
}
