package com.smalaca.trainingcenter.addressbook.domain.addressee;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyIdentifier
record AddresseeId(UUID id) {
}
