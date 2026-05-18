package com.smalaca.archetypes.partyrelationship.full;

public class PartyRole {
    private final Party party;
    private final RoleType roleType;

    public PartyRole(Party party, RoleType roleType) {
        this.party = party;
        this.roleType = roleType;
    }

    public Party getParty() {
        return party;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}
