package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import static com.smalaca.archetypes.partyrelationship.simple.FriendshipAssert.assertThat;

class FriendshipTest {
    @Test
    void shouldCreateFriendship() {
        Friendship friendship = new Friendship("P-001", "John Doe", "P-002", "Jane Doe");

        assertThat(friendship)
                .hasRelationshipType("Friendship")
                .hasFriendRoleType("Friend")
                .hasFriend1("P-001", "John Doe")
                .hasFriend2("P-002", "Jane Doe");
    }
}
