package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
@ArchetypeParty.PartyRoleIdentifier
record BuyerId(UUID id) {
}
