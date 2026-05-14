package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Organization
public class EnterpriseClient {
    private final Organization organization;
    @ArchetypeParty.PartyIdentifier
    private final String vatNumber;
    private final String industry;

    public EnterpriseClient(String name, String vatNumber, String industry) {
        this.organization = new Organization(name);
        this.vatNumber = vatNumber;
        this.industry = industry;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getIndustry() {
        return industry;
    }

    public Organization getOrganization() {
        return organization;
    }
}
