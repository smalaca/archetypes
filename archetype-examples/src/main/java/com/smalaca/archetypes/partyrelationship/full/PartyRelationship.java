package com.smalaca.archetypes.partyrelationship.full;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ArchetypePartyRelationship.PartyRelationship
public class PartyRelationship {
    private final RelationshipType relationshipType;
    private final List<PartyRole> roles = new ArrayList<>();

    public PartyRelationship(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void addRole(PartyRole role) {
        roles.add(role);
    }

    public Collection<PartyRole> getRoles() {
        return List.copyOf(roles);
    }
}
