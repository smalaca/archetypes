package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.smalaca.archetypes.partyrelationship.simple.MarriageAssert.assertThat;

class MarriageTest {
    private static final UUID PARTY_ID_1 = UUID.randomUUID();
    private static final UUID PARTY_ID_2 = UUID.randomUUID();

    @Test
    void shouldCreateMarriage() {
        Marriage marriage = new Marriage(PARTY_ID_1, PARTY_ID_2);

        assertThat(marriage)
                .hasSpouse1(PARTY_ID_1)
                .hasSpouse2(PARTY_ID_2);
    }
}
