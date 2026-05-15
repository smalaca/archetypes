package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Organization
public class EnterpriseClient extends Organization {
    @ArchetypeParty.PartyIdentifier
    private final VatNumber vatNumber;
    private final String industry;

    public EnterpriseClient(String name, VatNumber vatNumber, String industry) {
        super(name);
        this.vatNumber = vatNumber;
        this.industry = industry;
    }

    public VatNumber getVatNumber() {
        return vatNumber;
    }

    public String getIndustry() {
        return industry;
    }
}
