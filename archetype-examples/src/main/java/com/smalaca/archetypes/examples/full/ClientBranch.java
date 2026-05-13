package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

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
