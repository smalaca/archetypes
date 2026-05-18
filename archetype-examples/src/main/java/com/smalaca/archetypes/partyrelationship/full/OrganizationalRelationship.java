package com.smalaca.archetypes.partyrelationship.full;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRelationship
public class OrganizationalRelationship extends PartyRelationship {
    public OrganizationalRelationship(RelationshipType relationshipType) {
        super(relationshipType);
    }
}
