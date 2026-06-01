package com.smalaca.trainingcenter.trainerscatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
public record UserId(UUID id) {
}
