package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.examples.party.Organization;

@ArchetypeParty.Organization
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
