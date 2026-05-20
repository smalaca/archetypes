package com.smalaca.archetypes.partyrelationship.simple;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.smalaca.archetypes.partyrelationship.simple.FamilyAssert.assertThat;

class FamilyTest {
    private static final UUID PARENT_ID_1 = UUID.randomUUID();
    private static final UUID PARENT_ID_2 = UUID.randomUUID();
    private static final UUID CHILD_ID_1 = UUID.randomUUID();
    private static final UUID CHILD_ID_2 = UUID.randomUUID();

    @Test
    void shouldCreateFamily() {
        Family family = new Family();
        family.addParent(PARENT_ID_1);
        family.addParent(PARENT_ID_2);
        family.addChild(CHILD_ID_1);
        family.addChild(CHILD_ID_2);

        assertThat(family)
                .hasParentsCount(2)
                .hasChildrenCount(2)
                .hasParent(PARENT_ID_1)
                .hasParent(PARENT_ID_2)
                .hasChild(CHILD_ID_1)
                .hasChild(CHILD_ID_2);
    }
}
