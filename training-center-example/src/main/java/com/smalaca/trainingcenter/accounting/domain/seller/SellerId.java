package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
@ArchetypeParty.PartyRoleIdentifier
record SellerId(UUID id) {
}
