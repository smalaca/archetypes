package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
record TaxIdentificationNumber(String number, String countryCode) implements CompanyRegisteredIdentifier {
    @Override
    public String identifier() {
        return countryCode + " " + number;
    }
}
