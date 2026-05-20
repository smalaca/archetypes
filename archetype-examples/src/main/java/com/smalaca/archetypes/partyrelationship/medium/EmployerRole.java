package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRole
public class EmployerRole {
    @ArchetypePartyRelationship.Party
    private final UUID partyId;

    public EmployerRole(UUID partyId) {
        this.partyId = partyId;
    }

    public UUID getPartyId() {
        return partyId;
    }
}
