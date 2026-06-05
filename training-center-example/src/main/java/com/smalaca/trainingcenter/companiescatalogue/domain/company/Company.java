package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.List;

@ArchetypeParty.Organization
public class Company {
    private final CompanyId companyId;
    private final List<CompanyRegisteredIdentifier> registeredIdentifiers;

    Company(CompanyId companyId,  List<CompanyRegisteredIdentifier> registeredIdentifiers) {
        this.companyId = companyId;
        this.registeredIdentifiers = registeredIdentifiers;
    }
}
