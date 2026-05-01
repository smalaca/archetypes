package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.Address;
import com.smalaca.archetypes.party.PartyAuthentication;
import com.smalaca.archetypes.party.PartyIdentifier;

@Archetype(name = "Party")
public class ClientManagementService {
    @ArchetypePart(archetype = "Party", part = "Address")
    public void registerBranch(EnterpriseClient client, String city, String street) {
        client.addAddress(new Address(street, city, "N/A", "Country"));
        client.addUnit(new ClientBranch("Branch in " + city));
    }

    @ArchetypePart(archetype = "Party", part = "PartyIdentifier")
    public void assignTaxId(EnterpriseClient client, String taxId) {
        client.addIdentifier(new PartyIdentifier(taxId, "TAX_ID"));
    }

    @ArchetypePart(archetype = "Party", part = "PartyAuthentication")
    public void setupLogin(ClientContact contact, String username, String token) {
        contact.addAuthentication(new PartyAuthentication("OAUTH", token));
    }
}
