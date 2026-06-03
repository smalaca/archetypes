package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
record NationalBusinessRegister(String number) implements CompanyRegisteredIdentifier {
    @Override
    public String identifier() {
        return number;
    }
}
