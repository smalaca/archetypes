package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypePartyOrganizationUnit;
import com.smalaca.archetypes.examples.party.OrganizationUnit;

@ArchetypeParty
@ArchetypePartyOrganizationUnit
public class ClientBranch extends OrganizationUnit {
    public ClientBranch(String name) {
        super(name);
    }
}
