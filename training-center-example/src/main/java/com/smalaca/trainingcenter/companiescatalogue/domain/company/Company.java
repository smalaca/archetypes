package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.Party
@ArchetypeParty.Organization
public class Company {
    private final CompanyId companyId;
    private final List<CompanyRegisteredIdentifier> registeredIdentifiers;
    private final CompanyName primaryName;
    private final List<CompanyName> alternativeNames = new ArrayList<>();
    private final List<BusinessUnit> businessUnits = new ArrayList<>();

    public Company(CompanyId companyId, List<CompanyRegisteredIdentifier> registeredIdentifiers, CompanyName primaryName) {
        this.companyId = companyId;
        this.registeredIdentifiers = registeredIdentifiers;
        this.primaryName = primaryName;
    }
}
