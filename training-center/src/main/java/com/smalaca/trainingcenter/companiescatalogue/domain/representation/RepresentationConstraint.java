package com.smalaca.trainingcenter.companiescatalogue.domain.representation;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.BusinessUnitId;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.CompanyId;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRelationshipConstraint
interface RepresentationConstraint {
    boolean canRepresent(RepresentativeId representativeId, CompanyId companyId, BusinessUnitId businessUnitId);
}
