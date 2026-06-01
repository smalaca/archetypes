package com.smalaca.trainingcenter.addressbook.model;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyIdentifier
public record AddresseeId(UUID id) {
}
