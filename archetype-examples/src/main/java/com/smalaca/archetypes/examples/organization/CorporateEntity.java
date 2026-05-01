package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypePartyOrganization;
import com.smalaca.archetypes.examples.party.Organization;

@ArchetypeParty
@ArchetypePartyOrganization
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
