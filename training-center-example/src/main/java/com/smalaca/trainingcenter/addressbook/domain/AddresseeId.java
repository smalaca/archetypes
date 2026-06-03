package com.smalaca.trainingcenter.addressbook.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
public record AddresseeId(UUID id) {
}
