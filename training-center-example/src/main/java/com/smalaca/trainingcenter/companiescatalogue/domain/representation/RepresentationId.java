package com.smalaca.trainingcenter.companiescatalogue.domain.representation;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyIdentifier
record RepresentationId(UUID id) {
    public static RepresentationId representationId() {
        return new RepresentationId(UUID.randomUUID());
    }
}
