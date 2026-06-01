package com.smalaca.trainingcenter.accounting.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleIdentifier
public record TaxNumber(String value) {
}
