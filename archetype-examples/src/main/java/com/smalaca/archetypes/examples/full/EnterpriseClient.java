package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.examples.party.Organization;

@ArchetypeParty.Organization
public class EnterpriseClient extends Organization {
    private final String industry;

    public EnterpriseClient(String name, String industry) {
        super(name);
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }
}
