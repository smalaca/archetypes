package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
@ArchetypeParty.PartyRoleIdentifier
record SellerId(UUID id) {
}
