package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.companiescatalogue.domain.representation.Representation;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

@DomainDrivenDesign.Entity
@ArchetypeParty.Party
@ArchetypeParty.Organization
@ArchetypeParty.OrganizationUnit
record BusinessUnit(BusinessUnitId id, String name) {
    Representation represent(RepresentativeId representativeId) {
        return Representation.representation(representativeId, id);
    }
}
