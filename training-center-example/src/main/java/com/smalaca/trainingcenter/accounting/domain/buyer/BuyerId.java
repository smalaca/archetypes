package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
@ArchetypeParty.PartyRoleIdentifier
record BuyerId(UUID id) {
}
