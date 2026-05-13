package com.smalaca.archetypes.examples.simple;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleContactTest {
    @Test
    void shouldCreateSimpleContact() {
        SimpleContact contact = new SimpleContact("John", "Doe", "+48 123 456 789");

        assertThat(contact.getPerson().getFirstName()).isEqualTo("John");
        assertThat(contact.getPerson().getLastName()).isEqualTo("Doe");
        assertThat(contact.getPhoneNumber()).isEqualTo("+48 123 456 789");
    }
}
