package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRole
public class FriendRole {
    @ArchetypePartyRelationship.Party
    private final UUID partyId;

    public FriendRole(UUID partyId) {
        this.partyId = partyId;
    }

    public UUID getPartyId() {
        return partyId;
    }
}
