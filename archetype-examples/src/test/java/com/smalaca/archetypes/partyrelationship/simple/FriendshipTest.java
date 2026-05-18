package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.partyrelationship.simple.FriendshipAssert.assertThat;

class FriendshipTest {
    @Test
    void shouldCreateFriendship() {
        Friendship friendship = new Friendship("John Doe", "Jane Doe");

        assertThat(friendship).isBetween("John Doe", "Jane Doe");
    }
}
