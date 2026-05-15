package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
public record TaxIdentifier(String value) {
}
