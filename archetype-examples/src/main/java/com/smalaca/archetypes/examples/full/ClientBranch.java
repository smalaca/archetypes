package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class ClientBranch extends Organization {
    @ArchetypeParty.PartyIdentifier
    private final BranchCode branchCode;

    public ClientBranch(String name, BranchCode branchCode) {
        super(name);
        this.branchCode = branchCode;
    }

    public BranchCode getBranchCode() {
        return branchCode;
    }
}
