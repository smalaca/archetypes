package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRole
public class EmployerRole {
    @ArchetypePartyRelationship.RoleType
    private final String roleType = "Employer";
    @ArchetypePartyRelationship.Party
    private final String name;

    public EmployerRole(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getName() {
        return name;
    }
}
