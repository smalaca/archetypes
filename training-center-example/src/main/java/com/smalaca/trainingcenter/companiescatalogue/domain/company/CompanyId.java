package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
record CompanyId(UUID id) {
}
