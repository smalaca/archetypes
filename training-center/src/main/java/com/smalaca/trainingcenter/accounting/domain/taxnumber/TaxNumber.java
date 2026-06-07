package com.smalaca.trainingcenter.accounting.domain.taxnumber;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyRoleIdentifier
public record TaxNumber(String value) {
}
