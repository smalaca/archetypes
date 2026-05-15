package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Organization
public class CorporateEntity extends Organization {
    @ArchetypeParty.PartyIdentifier
    private final TaxIdentifier taxIdentifier;

    public CorporateEntity(String name, TaxIdentifier taxIdentifier) {
        super(name);
        this.taxIdentifier = taxIdentifier;
    }

    public TaxIdentifier getTaxIdentifier() {
        return taxIdentifier;
    }
}
