package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRelationship
public class Marriage {
    @ArchetypePartyRelationship.PartyRole
    private final SpouseRole spouse1;
    @ArchetypePartyRelationship.PartyRole
    private final SpouseRole spouse2;

    public Marriage(UUID partyId1, UUID partyId2) {
        this.spouse1 = new SpouseRole(partyId1);
        this.spouse2 = new SpouseRole(partyId2);
    }

    public SpouseRole getSpouse1() {
        return spouse1;
    }

    public SpouseRole getSpouse2() {
        return spouse2;
    }
}
