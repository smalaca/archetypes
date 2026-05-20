package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.smalaca.archetypes.partyrelationship.simple.FriendshipAssert.assertThat;

class FriendshipTest {
    private static final UUID PARTY_ID_1 = UUID.randomUUID();
    private static final UUID PARTY_ID_2 = UUID.randomUUID();

    @Test
    void shouldCreateFriendship() {
        Friendship friendship = new Friendship(PARTY_ID_1, PARTY_ID_2);

        assertThat(friendship)
                .hasFriend1(PARTY_ID_1)
                .hasFriend2(PARTY_ID_2);
    }
}
