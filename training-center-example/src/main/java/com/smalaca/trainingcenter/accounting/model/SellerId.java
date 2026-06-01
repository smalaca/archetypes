package com.smalaca.trainingcenter.accounting.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
@ArchetypeParty.PartyRoleIdentifier
public record SellerId(UUID id) {
}
