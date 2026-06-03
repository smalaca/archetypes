package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
record Login(String username) {
}
