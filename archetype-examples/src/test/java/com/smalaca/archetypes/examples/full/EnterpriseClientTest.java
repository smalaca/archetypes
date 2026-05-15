package com.smalaca.archetypes.examples.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.full.ClientContactAssert.assertThat;
import static com.smalaca.archetypes.examples.full.EnterpriseClientAssert.assertThat;

class EnterpriseClientTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageEnterpriseClient() {
        EnterpriseClient client = new EnterpriseClient("Enterprise Corp", new VatNumber("VAT-123456789"), "Technology");
        ClientContact contact = new ClientContact(new ClientContactName("Alice", "Smith"), "Manager");

        service.registerBranch(client, "Berlin", "Main Street 1", new BranchCode("BR-BER-01"));
        service.setupLogin(contact, "secure-token-123");

        assertThat(client)
                .hasName("Enterprise Corp")
                .hasVatNumber("VAT-123456789")
                .hasIndustry("Technology")
                .hasBranchIn("Berlin")
                .hasBranch("Branch in Berlin", "BR-BER-01");

        assertThat(contact)
                .hasName("Alice", "Smith")
                .hasPosition("Manager")
                .hasLoginToken("secure-token-123");
    }
}
