package com.smalaca.archetypes.party.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyAuthentication
public record ClientAuthentication(String type, String value) {
}
