package com.smalaca.trainingcenter.accounting.domain.taxnumber;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleIdentifier
public record TaxNumber(String value) {
}
