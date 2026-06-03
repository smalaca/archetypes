package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record UserId(UUID id) {
}
