package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class OrganizationUnit extends Organization {
    public OrganizationUnit(String name) {
        super(name);
    }
}
