package com.smalaca.archetypes.party.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty
public class ClientManagementService {
    @ArchetypeParty.OrganizationUnit
    public void registerBranch(EnterpriseClient client, String branchName, BranchCode branchCode) {
        client.addUnit(new ClientBranch(branchName, branchCode));
    }

    @ArchetypeParty.Address
    public void addAddress(Party party, String city, String street) {
        party.addAddress(new Address(street, city, "N/A", "Country"));
    }

    @ArchetypeParty.PartyAuthentication
    public void setupLogin(ClientContact contact, String token) {
        contact.setAuthentication(new ClientAuthentication("OAUTH", token));
    }
}
