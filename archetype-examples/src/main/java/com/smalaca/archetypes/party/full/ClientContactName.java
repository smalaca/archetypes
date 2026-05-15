package com.smalaca.archetypes.party.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
public record ClientContactName(String firstName, String lastName) {
}
