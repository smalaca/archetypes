package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record UserId(UUID id) {
}
