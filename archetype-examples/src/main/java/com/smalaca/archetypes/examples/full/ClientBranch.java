package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.party.OrganizationUnit;

@ArchetypeParty.OrganizationUnit
public class ClientBranch {
    private final OrganizationUnit organizationUnit;

    public ClientBranch(String name) {
        this.organizationUnit = new OrganizationUnit(name);
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }
}
