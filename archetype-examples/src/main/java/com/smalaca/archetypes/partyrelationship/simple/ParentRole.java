package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRole
public class ParentRole {
    @ArchetypePartyRelationship.RoleType
    private final String roleType = "Parent";
    @ArchetypePartyRelationship.Party
    private final String partyId;
    private final String person;

    public ParentRole(String partyId, String person) {
        this.partyId = partyId;
        this.person = person;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getPerson() {
        return person;
    }
}
