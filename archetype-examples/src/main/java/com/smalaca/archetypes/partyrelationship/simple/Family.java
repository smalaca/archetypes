package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.ArrayList;
import java.util.List;

@ArchetypePartyRelationship.PartyRelationship
public class Family {
    @ArchetypePartyRelationship.RelationshipType
    private final String relationshipType = "Family";
    @ArchetypePartyRelationship.PartyRole
    private final List<ParentRole> parents = new ArrayList<>();
    @ArchetypePartyRelationship.PartyRole
    private final List<ChildRole> children = new ArrayList<>();

    public void addParent(String partyId, String person) {
        parents.add(new ParentRole(partyId, person));
    }

    public void addChild(String partyId, String person) {
        children.add(new ChildRole(partyId, person));
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public List<ParentRole> getParents() {
        return parents;
    }

    public List<ChildRole> getChildren() {
        return children;
    }
}
