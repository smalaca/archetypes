package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
interface CompanyRegisteredIdentifier {
    String identifier();
}
