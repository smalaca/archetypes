package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class ClientBranch {
    private final OrganizationUnit organizationUnit;
    @ArchetypeParty.PartyIdentifier
    private final BranchCode branchCode;

    public ClientBranch(String name, BranchCode branchCode) {
        this.organizationUnit = new OrganizationUnit(name);
        this.branchCode = branchCode;
    }

    public BranchCode getBranchCode() {
        return branchCode;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }
}
