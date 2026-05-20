package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import static com.smalaca.archetypes.partyrelationship.simple.FamilyAssert.assertThat;

class FamilyTest {
    @Test
    void shouldCreateFamily() {
        Family family = new Family();
        family.addParent("P-001", "John Doe");
        family.addParent("P-002", "Jane Doe");
        family.addChild("P-003", "Jimmy Doe");
        family.addChild("P-004", "Jenny Doe");

        assertThat(family)
                .hasRelationshipType("Family")
                .hasParentsCount(2)
                .hasChildrenCount(2)
                .hasParent("P-001", "John Doe")
                .hasParent("P-002", "Jane Doe")
                .hasChild("P-003", "Jimmy Doe")
                .hasChild("P-004", "Jenny Doe");
    }
}
