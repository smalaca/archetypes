package com.smalaca.archetypes.examples.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.full.ClientContactAssert.assertThat;

class ClientContactTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageClientContactWithoutAddress() {
        ClientContact contact = new ClientContact(new ClientContactName("John", "Doe"), "Manager");
        service.setupLogin(contact, "token-123");

        assertThat(contact)
                .hasName("John", "Doe")
                .hasPosition("Manager")
                .hasLoginToken("token-123")
                .hasNoAddresses();
    }

    @Test
    void shouldManageClientContactWithAddress() {
        ClientContact contact = new ClientContact(new ClientContactName("Jane", "Smith"), "Director");
        service.setupLogin(contact, "token-456");
        service.addAddress(contact, "London", "Baker Street 221B");

        assertThat(contact)
                .hasName("Jane", "Smith")
                .hasPosition("Director")
                .hasLoginToken("token-456")
                .hasAddressIn("London", "Baker Street 221B");
    }

    @Test
    void shouldManageClientContactWithMultipleAddresses() {
        ClientContact contact = new ClientContact(new ClientContactName("John", "Constantine"), "Exorcist");
        service.addAddress(contact, "London", "Liverpool Street");
        service.addAddress(contact, "New York", "Broadway 1");

        assertThat(contact)
                .hasName("John", "Constantine")
                .hasPosition("Exorcist")
                .hasAddresses(2)
                .hasAddressIn("London", "Liverpool Street")
                .hasAddressIn("New York", "Broadway 1");
    }
}
