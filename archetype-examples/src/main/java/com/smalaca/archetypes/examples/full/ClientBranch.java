package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.OrganizationUnit;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "OrganizationUnit")
public class ClientBranch extends OrganizationUnit {
    public ClientBranch(String name) {
        super(name);
    }
}
