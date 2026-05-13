package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.examples.party.Address;
import com.smalaca.archetypes.examples.party.PartyAuthentication;
import com.smalaca.archetypes.examples.party.PartyIdentifier;

@ArchetypeParty
public class ClientManagementService {
    @ArchetypeParty.Address
    public void registerBranch(EnterpriseClient client, String city, String street) {
        client.addAddress(new Address(street, city, "N/A", "Country"));
        client.addUnit(new ClientBranch("Branch in " + city));
    }

    @ArchetypeParty.PartyIdentifier
    public void assignTaxId(EnterpriseClient client, String taxId) {
        client.addIdentifier(new PartyIdentifier(taxId, "TAX_ID"));
    }

    @ArchetypeParty.PartyAuthentication
    public void setupLogin(ClientContact contact, String username, String token) {
        contact.addAuthentication(new PartyAuthentication("OAUTH", token));
    }
}
