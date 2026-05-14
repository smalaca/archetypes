package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class ClientBranch {
    private final OrganizationUnit organizationUnit;
    @ArchetypeParty.PartyIdentifier
    private final String branchCode;

    public ClientBranch(String name, String branchCode) {
        this.organizationUnit = new OrganizationUnit(name);
        this.branchCode = branchCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }
}
