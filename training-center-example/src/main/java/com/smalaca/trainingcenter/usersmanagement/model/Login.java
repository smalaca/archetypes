package com.smalaca.trainingcenter.usersmanagement.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
public record Login(String username) {
}
