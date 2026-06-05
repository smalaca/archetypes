package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyIdentifier
record Login(String username) {
}
