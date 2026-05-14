package com.smalaca.archetypes.examples.simple;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.simple.SimpleContactAssert.assertThat;

class SimpleContactTest {
    @Test
    void shouldCreateSimpleContact() {
        SimpleContact contact = new SimpleContact("jdoe", "John", "Doe", "+48 123 456 789");

        assertThat(contact)
                .hasUsername("jdoe")
                .hasPerson("John", "Doe")
                .hasPhoneNumber("+48 123 456 789");
    }
}
