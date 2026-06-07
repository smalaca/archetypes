package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.companiescatalogue.domain.representation.Representation;
import com.smalaca.trainingcenter.companiescatalogue.domain.representation.RepresentationFactory;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Representation> represent(RepresentativeId representativeId, BusinessUnitId businessUnitId, RepresentationFactory factory) {
        if (isBusinessUnitExist(businessUnitId)) {
            return factory.representation(representativeId, companyId, businessUnitId);
        } else {
            return Optional.empty();
        }
    }

    private boolean isBusinessUnitExist(BusinessUnitId businessUnitId) {
        return businessUnits.stream().anyMatch(businessUnit -> businessUnit.hasId(businessUnitId));
    }

    public void add(BusinessUnit businessUnit) {
        businessUnits.add(businessUnit);
    }

    public void add(CompanyName alternativeName) {
        alternativeNames.add(alternativeName);
    }
}
