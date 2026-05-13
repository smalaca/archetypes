package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.party.Address;
import com.smalaca.archetypes.party.PartyAuthentication;
import com.smalaca.archetypes.party.PartyIdentifier;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EnterpriseClientTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageEnterpriseClient() {
        EnterpriseClient client = new EnterpriseClient("Enterprise Corp", "Technology");
        ClientContact contact = new ClientContact("Alice", "Smith", "Manager");

        service.assignTaxId(client, "VAT-987654321");
        service.registerBranch(client, "Berlin", "Main Street 1");
        service.setupLogin(contact, "alice.smith", "secure-token-123");

        assertThat(client.getOrganization().getName()).isEqualTo("Enterprise Corp");
        assertThat(client.getIndustry()).isEqualTo("Technology");
        
        assertThat(client.getOrganization().getIdentifiers())
                .extracting(PartyIdentifier::identifier)
                .containsExactly("VAT-987654321");

        assertThat(client.getOrganization().getAddresses())
                .extracting(Address::city)
                .containsExactly("Berlin");

        assertThat(contact.getPerson().getAuthentications())
                .extracting(PartyAuthentication::value)
                .containsExactly("secure-token-123");
        
        assertThat(contact.getPosition()).isEqualTo("Manager");
    }
}
