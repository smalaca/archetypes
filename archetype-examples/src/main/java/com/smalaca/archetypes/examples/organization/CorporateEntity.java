package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.Organization;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "Organization")
public class CorporateEntity extends Organization {
    private final String taxIdentifier;

    public CorporateEntity(String name, String taxIdentifier) {
        super(name);
        this.taxIdentifier = taxIdentifier;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }
}
