package com.smalaca.trainingcenter.accounting.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleIdentifier
public record TaxNumber(String value) {
}
