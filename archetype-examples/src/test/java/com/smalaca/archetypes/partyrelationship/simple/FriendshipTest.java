package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import static com.smalaca.archetypes.partyrelationship.simple.FriendshipAssert.assertThat;

class FriendshipTest {
    @Test
    void shouldCreateFriendship() {
        Friendship friendship = new Friendship("John Doe", "Jane Doe");

        assertThat(friendship)
                .hasRelationshipType("Friendship")
                .hasFriendRoleType("Friend")
                .hasFriend1("John Doe")
                .hasFriend2("Jane Doe");
    }
}
