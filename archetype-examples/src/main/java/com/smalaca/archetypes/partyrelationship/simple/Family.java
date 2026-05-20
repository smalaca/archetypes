package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ArchetypePartyRelationship.PartyRelationship
public class Family {
    @ArchetypePartyRelationship.PartyRole
    private final List<ParentRole> parents = new ArrayList<>();
    @ArchetypePartyRelationship.PartyRole
    private final List<ChildRole> children = new ArrayList<>();

    public void addParent(UUID partyId) {
        parents.add(new ParentRole(partyId));
    }

    public void addChild(UUID partyId) {
        children.add(new ChildRole(partyId));
    }

    public List<ParentRole> getParents() {
        return parents;
    }

    public List<ChildRole> getChildren() {
        return children;
    }
}
