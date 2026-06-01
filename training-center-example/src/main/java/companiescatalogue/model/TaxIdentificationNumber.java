package companiescatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.RegisteredIdentifier
public record TaxIdentificationNumber(String number, String countryCode) implements CompanyRegisteredIdentifier {
    @Override
    public String identifier() {
        return countryCode + " " + number;
    }
}
