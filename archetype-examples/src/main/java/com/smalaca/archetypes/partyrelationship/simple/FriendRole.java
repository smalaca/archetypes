package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRole
public class FriendRole {
    @ArchetypePartyRelationship.RoleType
    private final String roleType = "Friend";
    @ArchetypePartyRelationship.Party
    private final String person;

    public FriendRole(String person) {
        this.person = person;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getPerson() {
        return person;
    }
}
