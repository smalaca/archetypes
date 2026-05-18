package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRelationship
public class Friendship {
    private final String person1;
    private final String person2;

    public Friendship(String person1, String person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    public String getPerson1() {
        return person1;
    }

    public String getPerson2() {
        return person2;
    }
}
