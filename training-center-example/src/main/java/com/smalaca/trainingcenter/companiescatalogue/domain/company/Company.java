package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.Party
@ArchetypeParty.Organization
public class Company {
    private final CompanyId companyId;
    private final List<CompanyRegisteredIdentifier> registeredIdentifiers;

    Company(CompanyId companyId,  List<CompanyRegisteredIdentifier> registeredIdentifiers) {
        this.companyId = companyId;
        this.registeredIdentifiers = registeredIdentifiers;
    }
}
