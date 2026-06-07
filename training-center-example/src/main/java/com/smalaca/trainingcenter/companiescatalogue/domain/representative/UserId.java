package com.smalaca.trainingcenter.companiescatalogue.domain.representative;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyIdentifier
record UserId(UUID id) {
}
