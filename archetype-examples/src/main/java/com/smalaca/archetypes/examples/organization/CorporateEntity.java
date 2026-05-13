package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.party.Organization;

@ArchetypeParty.Organization
public class CorporateEntity {
    private final Organization organization;
    private final String taxIdentifier;

    public CorporateEntity(String name, String taxIdentifier) {
        this.organization = new Organization(name);
        this.taxIdentifier = taxIdentifier;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public Organization getOrganization() {
        return organization;
    }
}
