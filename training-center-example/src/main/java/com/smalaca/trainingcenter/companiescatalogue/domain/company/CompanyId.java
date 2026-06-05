package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record CompanyId(UUID id) {
}
