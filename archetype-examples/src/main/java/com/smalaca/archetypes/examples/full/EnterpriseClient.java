package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.Organization;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "Organization")
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
