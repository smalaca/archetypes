package com.smalaca.trainingcenter.companiescatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.List;

@ArchetypeParty.Organization
public class Company {
    private final CompanyId companyId;
    private final List<CompanyRegisteredIdentifier> registeredIdentifiers;

    public Company(CompanyId companyId,  List<CompanyRegisteredIdentifier> registeredIdentifiers) {
        this.companyId = companyId;
        this.registeredIdentifiers = registeredIdentifiers;
    }
}
