package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyAuthentication
public record ClientAuthentication(String type, String value) {
}
