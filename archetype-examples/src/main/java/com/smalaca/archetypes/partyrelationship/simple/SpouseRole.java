package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRole
public class SpouseRole {
    @ArchetypePartyRelationship.Party
    private final UUID partyId;

    public SpouseRole(UUID partyId) {
        this.partyId = partyId;
    }

    public UUID getPartyId() {
        return partyId;
    }
}
