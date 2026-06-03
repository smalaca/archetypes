package com.smalaca.trainingcenter.addressbook.domain.addressee;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record AddresseeId(UUID id) {
}
