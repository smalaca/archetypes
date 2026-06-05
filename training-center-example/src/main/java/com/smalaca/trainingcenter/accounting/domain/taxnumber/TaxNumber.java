package com.smalaca.trainingcenter.accounting.domain.taxnumber;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleIdentifier
public record TaxNumber(String value) {
}
