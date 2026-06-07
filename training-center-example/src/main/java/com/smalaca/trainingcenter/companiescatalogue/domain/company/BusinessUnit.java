package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Entity
@ArchetypeParty.Party
@ArchetypeParty.Organization
@ArchetypeParty.OrganizationUnit
record BusinessUnit(BusinessUnitId id, String name) {
}
