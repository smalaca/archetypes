package com.smalaca.trainingcenter.companiescatalogue.domain.representation;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.BusinessUnitId;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.PartyRelationship
public class Representation {
    private final RepresentationId representationId;
    private final RepresentativeId representativeId;
    private final BusinessUnitId businessUnitId;

    private Representation(RepresentationId representationId, RepresentativeId representativeId, BusinessUnitId businessUnitId) {
        this.representationId = representationId;
        this.representativeId = representativeId;
        this.businessUnitId = businessUnitId;
    }

    @DomainDrivenDesign.Factory
    static Representation representation(RepresentativeId representativeId, BusinessUnitId businessUnitId) {
        return new Representation(RepresentationId.representationId(), representativeId, businessUnitId);
    }
}
