package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import static com.smalaca.archetypes.partyrelationship.simple.MarriageAssert.assertThat;

class MarriageTest {
    @Test
    void shouldCreateMarriage() {
        Marriage marriage = new Marriage("P-001", "John Doe", "P-002", "Jane Doe");

        assertThat(marriage)
                .hasRelationshipType("Marriage")
                .hasSpouseRoleType("Spouse")
                .hasSpouse1("P-001", "John Doe")
                .hasSpouse2("P-002", "Jane Doe");
    }
}
