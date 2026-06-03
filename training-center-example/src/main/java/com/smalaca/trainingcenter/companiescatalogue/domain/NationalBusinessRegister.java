package com.smalaca.trainingcenter.companiescatalogue.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
public record NationalBusinessRegister(String number) implements CompanyRegisteredIdentifier {
    @Override
    public String identifier() {
        return number;
    }
}
