package com.smalaca.archetypes.examples.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.full.EnterpriseClientAssert.assertThat;

class EnterpriseClientTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageEnterpriseClientWithBranchesAndAddresses() {
        EnterpriseClient client = new EnterpriseClient("Enterprise Corp", new VatNumber("VAT-123456789"), "Technology");
        
        service.addAddress(client, "Berlin", "Main Street 1");
        service.registerBranch(client, "Branch Berlin", new BranchCode("BR-BER-01"));
        service.registerBranch(client, "Branch Munich", new BranchCode("BR-MUN-01"));
        
        ClientBranch berlinBranch = client.getUnit("Branch Berlin");
        service.addAddress(berlinBranch, "Berlin", "Branch Street 1");
        
        ClientBranch munichBranch = client.getUnit("Branch Munich");
        munichBranch.addUnit(new ClientBranch("Munich Sales", new BranchCode("BR-MUN-SAL-01")));

        assertThat(client)
                .hasName("Enterprise Corp")
                .hasVatNumber("VAT-123456789")
                .hasIndustry("Technology")
                .hasAddressIn("Berlin")
                .hasBranches(2)
                .hasBranch("Branch Berlin", "BR-BER-01")
                .hasBranchAddressIn("Branch Berlin", "Berlin")
                .hasBranch("Branch Munich", "BR-MUN-01")
                .hasSubBranch("Branch Munich", "Munich Sales", "BR-MUN-SAL-01");
    }

    @Test
    void shouldManageEnterpriseClientWithNoBranches() {
        EnterpriseClient client = new EnterpriseClient("Startup Inc", new VatNumber("VAT-987654321"), "Retail");

        assertThat(client)
                .hasName("Startup Inc")
                .hasVatNumber("VAT-987654321")
                .hasIndustry("Retail")
                .hasNoBranches();
    }
}
