package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty
public class ClientManagementService {
    @ArchetypeParty.Address
    public void registerBranch(EnterpriseClient client, String city, String street, BranchCode branchCode) {
        client.addAddress(new Address(street, city, "N/A", "Country"));
        client.addUnit(new ClientBranch("Branch in " + city, branchCode));
    }

    @ArchetypeParty.PartyAuthentication
    public void setupLogin(ClientContact contact, String token) {
        contact.addAuthentication(new ClientAuthentication("OAUTH", token));
    }
}
