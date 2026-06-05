package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyIdentifier
record Login(String username) {
}
