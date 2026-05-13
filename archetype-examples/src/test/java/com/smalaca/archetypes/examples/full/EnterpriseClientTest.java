package com.smalaca.archetypes.examples.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.full.ClientContactAssert.assertThat;
import static com.smalaca.archetypes.examples.full.EnterpriseClientAssert.assertThat;

class EnterpriseClientTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageEnterpriseClient() {
        EnterpriseClient client = new EnterpriseClient("Enterprise Corp", "Technology");
        ClientContact contact = new ClientContact("Alice", "Smith", "Manager");

        service.assignTaxId(client, "VAT-987654321");
        service.registerBranch(client, "Berlin", "Main Street 1");
        service.setupLogin(contact, "alice.smith", "secure-token-123");

        assertThat(client)
                .hasName("Enterprise Corp")
                .hasIndustry("Technology")
                .hasTaxId("VAT-987654321")
                .hasBranchIn("Berlin");

        assertThat(contact)
                .hasPosition("Manager")
                .hasLoginToken("secure-token-123");
    }
}
