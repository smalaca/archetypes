package com.smalaca.trainingcenter.companiescatalogue.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyIdentifier
public record CompanyId(UUID id) {
}
