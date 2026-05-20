package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRelationship
public class Marriage {
    @ArchetypePartyRelationship.RelationshipType
    private final String relationshipType = "Marriage";
    @ArchetypePartyRelationship.PartyRole
    private final SpouseRole spouse1;
    @ArchetypePartyRelationship.PartyRole
    private final SpouseRole spouse2;

    public Marriage(String partyId1, String person1, String partyId2, String person2) {
        this.spouse1 = new SpouseRole(partyId1, person1);
        this.spouse2 = new SpouseRole(partyId2, person2);
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public SpouseRole getSpouse1() {
        return spouse1;
    }

    public SpouseRole getSpouse2() {
        return spouse2;
    }
}
