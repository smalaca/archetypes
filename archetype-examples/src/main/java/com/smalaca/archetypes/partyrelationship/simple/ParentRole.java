package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRole
public class ParentRole {
    @ArchetypePartyRelationship.Party
    private final UUID partyId;

    public ParentRole(UUID partyId) {
        this.partyId = partyId;
    }

    public UUID getPartyId() {
        return partyId;
    }
}
