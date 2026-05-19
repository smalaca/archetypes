package com.smalaca.archetypes.partyrelationship.full;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyIdentifier
public record PartyIdentifier(String identifier, String type) {
}
