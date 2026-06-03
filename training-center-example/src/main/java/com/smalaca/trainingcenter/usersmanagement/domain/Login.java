package com.smalaca.trainingcenter.usersmanagement.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
public record Login(String username) {
}
