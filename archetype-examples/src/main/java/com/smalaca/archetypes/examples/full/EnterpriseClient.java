package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.party.Organization;

@ArchetypeParty.Organization
public class EnterpriseClient {
    private final Organization organization;
    private final String industry;

    public EnterpriseClient(String name, String industry) {
        this.organization = new Organization(name);
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    public Organization getOrganization() {
        return organization;
    }
}
