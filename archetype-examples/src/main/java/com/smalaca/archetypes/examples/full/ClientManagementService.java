package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty
public class ClientManagementService {
    @ArchetypeParty.Address
    public void registerBranch(EnterpriseClient client, String city, String street, String branchCode) {
        client.getOrganization().addAddress(new Address(street, city, "N/A", "Country"));
        client.getOrganization().addUnit(new ClientBranch("Branch in " + city, branchCode).getOrganizationUnit());
    }

    @ArchetypeParty.PartyIdentifier
    public void assignTaxId(EnterpriseClient client, String taxId) {
        client.getOrganization().addIdentifier(new PartyIdentifier(taxId, "TAX_ID"));
    }

    @ArchetypeParty.PartyAuthentication
    public void setupLogin(ClientContact contact, String username, String token) {
        contact.getPerson().addAuthentication(new PartyAuthentication("OAUTH", token));
    }
}
