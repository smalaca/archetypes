package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
interface CompanyRegisteredIdentifier {
    String identifier();
}
